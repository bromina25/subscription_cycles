package com.lufthansa.subscriptions.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubscriptionStatus {

    ACTIVE("Active"),
    PAUSED("Paused"),
    CANCELED("Canceled");

    public final String name;
}
