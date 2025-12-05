package com.lufthansa.subscriptions.service.interfaces;

import com.lufthansa.subscriptions.constant.enums.BillingPeriod;
import com.lufthansa.subscriptions.constant.enums.SubscriptionStatus;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDetailsDto;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDto;
import com.lufthansa.subscriptions.entity.Subscription;

import java.time.LocalDate;
import java.util.List;

public interface ISubscriptionService {

    Subscription createSubscription(SubscriptionDto subscriptionDto);

    SubscriptionDetailsDto getSubscriptionDetails(Long id);

    List<Subscription> findByStatusAndCurrentPeriodEndBefore(SubscriptionStatus status, LocalDate date);

    LocalDate calculateCurrentPeriodEnd(LocalDate startDate, BillingPeriod billingPeriod);

    Subscription save(Subscription subscription);
}
