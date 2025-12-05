package com.lufthansa.subscriptions.controller;

import com.lufthansa.subscriptions.dto.general.CustomPage;
import com.lufthansa.subscriptions.dto.general.EntityIdDto;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDetailsDto;
import com.lufthansa.subscriptions.dto.subscriptionPlan.SubscriptionPlanDto;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import com.lufthansa.subscriptions.apiDoc.SubscriptionControllerDocs;
import com.lufthansa.subscriptions.constant.RestConstants;
import com.lufthansa.subscriptions.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.lufthansa.subscriptions.constant.RestConstants.*;
import static com.lufthansa.subscriptions.constant.RestConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.SubscriptionPlanController.BASE_PATH)
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @PostMapping
    @SubscriptionControllerDocs.CreateSubscriptionDoc
    public ResponseEntity<EntityIdDto> createSubscriptionPlan(@Validated @RequestBody SubscriptionPlanDto subscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.createSubscriptionPlan(subscriptionPlanDto);
        return new ResponseEntity<>(EntityIdDto.of(subscriptionPlan.getId()), HttpStatus.CREATED);
    }

    @GetMapping
    @SubscriptionControllerDocs.GetSubscriptionDetailsDoc
    public ResponseEntity<CustomPage<SubscriptionPlanDetailsDto>> getSubscriptionPlan(@RequestParam(required = false) String name,
                                                                                      @RequestParam(value = PAGE, defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                                                      @RequestParam(value = SIZE, defaultValue = DEFAULT_PAGE_SIZE) int size){
        Page<SubscriptionPlanDetailsDto> subscriptionPlan = subscriptionPlanService.getSubscriptionPlan(name, page, size);
        return ResponseEntity.ok(new CustomPage<>(subscriptionPlan));
    }
}
