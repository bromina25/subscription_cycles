package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Payment;
import com.lufthansa.subscriptions.repository.PaymentRepository;
import com.lufthansa.subscriptions.service.interfaces.IPaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService extends BaseService<Payment> implements IPaymentService {

    public PaymentService(PaymentRepository paymentRepository) {
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
