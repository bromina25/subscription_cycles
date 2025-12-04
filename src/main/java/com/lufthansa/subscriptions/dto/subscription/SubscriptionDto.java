package com.lufthansa.subscriptions.dto.subscription;

import com.lufthansa.subscriptions.dto.customer.CustomerDto;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionDto {

    private Long id;

    @NotNull(message = "Customer is required!")
    private CustomerDto customer;

    @NotNull(message = "Plan is required!")
    private SubscriptionPlanDto plan;
}
