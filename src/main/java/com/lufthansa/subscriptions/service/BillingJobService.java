package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.constant.enums.SubscriptionStatus;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Subscription;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import com.lufthansa.subscriptions.service.interfaces.IBillingJobService;
import com.lufthansa.subscriptions.service.interfaces.IInvoiceService;
import com.lufthansa.subscriptions.service.interfaces.ISubscriptionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BillingJobService implements IBillingJobService {

    private final ISubscriptionService iSubscriptionService;
    private final IInvoiceService iInvoiceService;

    @Transactional(rollbackOn = Exception.class)
    public int processBillingCycle() {
        List<Subscription> expiredSubs = iSubscriptionService.findByStatusAndCurrentPeriodEndBefore(
                SubscriptionStatus.ACTIVE, LocalDate.now());

        for (Subscription sub : expiredSubs) {
            renewSubscription(sub);
        }
        return expiredSubs.size();
    }

    private void renewSubscription(Subscription sub) {
        SubscriptionPlan plan = sub.getPlan();

        LocalDate newPeriodStart = sub.getCurrentPeriodEnd();
        LocalDate newPeriodEnd = iSubscriptionService.calculateCurrentPeriodEnd(newPeriodStart, plan.getBillingPeriod());

        Invoice invoice = Invoice.builder()
                .subscription(sub)
                .periodStart(newPeriodStart)
                .periodEnd(newPeriodEnd)
                .amount(iInvoiceService.calculateInvoiceAmount(plan))
                .invoiceStatus(InvoiceStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(7))
                .build();

        iInvoiceService.save(invoice);

        sub.setCurrentPeriodStart(newPeriodStart);
        sub.setCurrentPeriodEnd(newPeriodEnd);

        iSubscriptionService.save(sub);
    }
}
