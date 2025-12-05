package com.lufthansa.subscriptions.service.interfaces;

import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.entity.Payment;

public interface IPaymentService {

    Payment buildAndSavePayment(Invoice invoice, PaymentRegistrationDto paymentDto);
}