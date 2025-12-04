package com.lufthansa.subscriptions.dto.payment;

import com.lufthansa.subscriptions.constant.enums.PaymentMethod;
import com.lufthansa.subscriptions.constant.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRegistrationDto {

    @NotNull(message = "Payment method is required!")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment status method is required!")
    private PaymentStatus paymentStatus;
}
