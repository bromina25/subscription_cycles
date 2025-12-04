package com.lufthansa.subscriptions.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BillingPeriod {

    MONTHLY("Monthly"),
    YEARLY("Yearly");

    public final String name;
}
