package com.lufthansa.subscriptions.service.serviceImpl;

import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.constant.enums.SubscriptionStatus;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Subscription;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import com.lufthansa.subscriptions.service.BillingJobService;
import com.lufthansa.subscriptions.service.InvoiceService;
import com.lufthansa.subscriptions.service.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BillingJobServiceImpl implements BillingJobService {

    private final SubscriptionService subscriptionService;
    private final InvoiceService invoiceService;

    @Transactional(rollbackOn = Exception.class)
    public int processBillingCycle() {
        List<Subscription> expiredSubs = subscriptionService.findByStatusAndCurrentPeriodEndBefore(
                SubscriptionStatus.ACTIVE, LocalDate.now());

        for (Subscription sub : expiredSubs) {
            renewSubscription(sub);
        }
        return expiredSubs.size();
    }

    private void renewSubscription(Subscription sub) {
        SubscriptionPlan plan = sub.getPlan();

        LocalDate newPeriodStart = sub.getCurrentPeriodEnd();
        LocalDate newPeriodEnd = subscriptionService.calculateCurrentPeriodEnd(newPeriodStart, plan.getBillingPeriod());

        Invoice invoice = Invoice.builder()
                .subscription(sub)
                .periodStart(newPeriodStart)
                .periodEnd(newPeriodEnd)
                .amount(invoiceService.calculateInvoiceAmount(plan))
                .invoiceStatus(InvoiceStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(7))
                .build();

        invoiceService.save(invoice);

        sub.setCurrentPeriodStart(newPeriodStart);
        sub.setCurrentPeriodEnd(newPeriodEnd);

        subscriptionService.save(sub);
    }
}
