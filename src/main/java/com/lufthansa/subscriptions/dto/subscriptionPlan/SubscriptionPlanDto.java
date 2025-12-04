package com.lufthansa.subscriptions.dto.subscriptionPlan;

import com.lufthansa.subscriptions.constant.enums.BillingPeriod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionPlanDto {

    private Long id;

    @NotNull(message = "Name is required!")
    private String name;

    @NotNull(message = "Price per month is required!")
    @Positive(message = "Price per month should be a positive value!")
    private Double pricePerMonth;

    @NotNull(message = "Billing period is required!")
    private BillingPeriod billingPeriod;

    @NotNull(message = "Max users is required!")
    private Integer maxUsers;
}
