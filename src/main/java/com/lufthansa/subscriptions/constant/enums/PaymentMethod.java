package com.lufthansa.subscriptions.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CARD("Card"),
    BANK_TRANSFER("Bank Transfer");

    public final String name;
}
