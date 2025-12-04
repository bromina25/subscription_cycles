package com.lufthansa.subscriptions.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

    SUCCESS("Success"),
    FAILED("Failed");

    public final String name;
}
