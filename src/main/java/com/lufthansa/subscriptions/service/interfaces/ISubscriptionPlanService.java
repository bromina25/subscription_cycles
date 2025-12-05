package com.lufthansa.subscriptions.service.interfaces;

import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDetailsDto;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDto;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import org.springframework.data.domain.Page;

public interface ISubscriptionPlanService {

    SubscriptionPlan createSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto);

    Page<SubscriptionPlanDetailsDto> getSubscriptionPlan(String name, int page, int size);

    SubscriptionPlan findById(Long id, String entity);
}