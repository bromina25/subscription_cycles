package com.lufthansa.subscriptions.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceStatus {

    PENDING("Pending"),
    PAID("Paid"),
    FAILED("Failed");

    public final String name;
}
