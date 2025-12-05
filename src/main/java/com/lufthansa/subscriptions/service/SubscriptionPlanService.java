package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDetailsDto;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDto;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import org.springframework.data.domain.Page;

public interface SubscriptionPlanService {

    SubscriptionPlan createSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto);

    Page<SubscriptionPlanDetailsDto> getSubscriptionPlan(String name, int page, int size);

    SubscriptionPlan findById(Long id, String entity);
}