package com.lufthansa.subscriptions.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivationState {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    public final String name;
}
