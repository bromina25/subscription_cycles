package com.lufthansa.subscriptions.service.serviceImpl;

import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.dto.invoice.InvoiceDto;
import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Payment;
import com.lufthansa.subscriptions.entity.Subscription;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import com.lufthansa.subscriptions.exception.BadRequestException;
import com.lufthansa.subscriptions.repository.InvoiceRepository;
import com.lufthansa.subscriptions.service.BaseService;
import com.lufthansa.subscriptions.service.InvoiceService;
import com.lufthansa.subscriptions.service.PaymentService;
import com.lufthansa.subscriptions.util.DisplayHelper;
import com.lufthansa.subscriptions.util.JsonUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InvoiceServiceImpl extends BaseService<Invoice> implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentService paymentService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, PaymentService paymentService) {
        super(invoiceRepository);
        this.invoiceRepository = invoiceRepository;
        this.paymentService = paymentService;
    }

    @Override
    public Page<Invoice> findInvoicesByCustomerId(Long customerId, Pageable pageable) {
        return invoiceRepository.findInvoicesByCustomerId(customerId, pageable);
    }

    @Override
    public void generateInvoice(Subscription subscription) {
        Invoice invoice = Invoice.builder()
                .subscription(subscription)
                .amount(DisplayHelper.formatNumbersWithTwoDecimal(calculateInvoiceAmount(subscription.getPlan())))
                .invoiceStatus(InvoiceStatus.PENDING)
                .periodStart(subscription.getCurrentPeriodStart())
                .periodEnd(subscription.getCurrentPeriodEnd())
                .dueDate(LocalDate.now().plusDays(7))
                .build();

        save(invoice);
    }

    @Override
    public double calculateInvoiceAmount(SubscriptionPlan plan) {
        return switch (plan.getBillingPeriod()) {
            case MONTHLY -> plan.getPricePerMonth();
            case YEARLY -> plan.getPricePerMonth() * 12;
        };
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Invoice registerInvoicePayment(Long invoiceId, PaymentRegistrationDto paymentDto) {
        Invoice invoice = findById(invoiceId, Invoice.class.getSimpleName());
        validateInvoicePayment(invoice);

        Payment payment = paymentService.buildAndSavePayment(invoice, paymentDto);
        return updateInvoiceStatusBasedOnPayment(invoice, payment);
    }

    private Invoice updateInvoiceStatusBasedOnPayment(Invoice invoice, Payment payment) {
        InvoiceStatus status = switch (payment.getPaymentStatus()) {
            case SUCCESS -> InvoiceStatus.PAID;
            case FAILED -> InvoiceStatus.FAILED;
        };
        invoice.setInvoiceStatus(status);
        return save(invoice);
    }

    private void validateInvoicePayment(Invoice invoice) {
        if (invoice.getInvoiceStatus() != InvoiceStatus.PENDING) {
            throw new BadRequestException("Payments may only apply to PENDING invoices!");
        }
    }

    @Override
    public Page<InvoiceDto> getInvoices(Long customerId, InvoiceStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Invoice> invoices = invoiceRepository.findInvoices(customerId, status, pageable);
        return invoices.map(invoice -> JsonUtil.map(invoice, InvoiceDto.class));
    }
}