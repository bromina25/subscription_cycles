package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.dto.invoice.InvoiceDto;
import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Subscription;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {

    Invoice registerInvoicePayment(Long invoiceId, PaymentRegistrationDto paymentRegistrationDto);

    Page<InvoiceDto> getInvoices(Long customerId, InvoiceStatus status, int page, int size);

    Page<Invoice> findInvoicesByCustomerId(Long customerId, Pageable pageable);

    void generateInvoice(Subscription subscription);

    double calculateInvoiceAmount(SubscriptionPlan plan);

    Invoice save(Invoice invoice);
}

