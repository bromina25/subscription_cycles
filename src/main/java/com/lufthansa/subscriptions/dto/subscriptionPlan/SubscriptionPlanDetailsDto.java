package com.lufthansa.subscriptions.dto.subscriptionPlan;

import com.lufthansa.subscriptions.constant.enums.ActivationState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionPlanDetailsDto extends SubscriptionPlanDto {

    private ActivationState activationState;
}
