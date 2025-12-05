package com.lufthansa.subscriptions.service.serviceImpl;

import com.lufthansa.subscriptions.constant.enums.ActivationState;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDetailsDto;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDto;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import com.lufthansa.subscriptions.repository.SubscriptionPlanRepository;
import com.lufthansa.subscriptions.service.BaseService;
import com.lufthansa.subscriptions.service.SubscriptionPlanService;
import com.lufthansa.subscriptions.util.JsonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanServiceImpl extends BaseService<SubscriptionPlan> implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlanServiceImpl(SubscriptionPlanRepository subscriptionPlanRepository) {
        super(subscriptionPlanRepository);
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto) {
        return save(buildSubscriptionPlan(subscriptionPlanDto));
    }

    private SubscriptionPlan buildSubscriptionPlan(SubscriptionPlanDto dto) {
        return SubscriptionPlan.builder()
                .id(dto.getId())
                .name(dto.getName())
                .pricePerMonth(dto.getPricePerMonth())
                .billingPeriod(dto.getBillingPeriod())
                .maxUsers(dto.getMaxUsers())
                .activationState(ActivationState.ACTIVE)
                .build();
    }

    @Override
    public Page<SubscriptionPlanDetailsDto> getSubscriptionPlan(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<SubscriptionPlan> plans = subscriptionPlanRepository.findAll(name, pageable);

        return plans.map(plan -> JsonUtil.map(plan, SubscriptionPlanDetailsDto.class));
    }
}
