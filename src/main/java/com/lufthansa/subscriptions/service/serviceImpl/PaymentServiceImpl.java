package com.lufthansa.subscriptions.service.serviceImpl;

import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Payment;
import com.lufthansa.subscriptions.repository.PaymentRepository;
import com.lufthansa.subscriptions.service.BaseService;
import com.lufthansa.subscriptions.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl extends BaseService<Payment> implements PaymentService {

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public Payment buildAndSavePayment(Invoice invoice, PaymentRegistrationDto paymentDto) {
        Payment payment = Payment.builder()
                .invoice(invoice)
                .amount(invoice.getAmount())
                .paymentMethod(paymentDto.getPaymentMethod())
                .paymentStatus(paymentDto.getPaymentStatus())
                .createdAt(LocalDateTime.now())
                .build();

        return save(payment);
    }
}
