package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Payment;

public interface PaymentService {

    Payment buildAndSavePayment(Invoice invoice, PaymentRegistrationDto paymentDto);
}