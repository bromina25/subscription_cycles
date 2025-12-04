package com.lufthansa.subscriptions.dto.subscription;

import com.lufthansa.subscriptions.constant.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionDetailsDto extends SubscriptionDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private SubscriptionStatus status;

    private LocalDate currentPeriodStart;

    private LocalDate currentPeriodEnd;
}
