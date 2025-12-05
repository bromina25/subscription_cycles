package com.lufthansa.subscriptions.controller;

import com.lufthansa.subscriptions.dto.general.EntityIdDto;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDto;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDetailsDto;
import com.lufthansa.subscriptions.entity.Subscription;
import com.lufthansa.subscriptions.apiDoc.SubscriptionControllerDocs;
import com.lufthansa.subscriptions.constant.RestConstants;
import com.lufthansa.subscriptions.service.interfaces.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.SubscriptionController.BASE_PATH)
public class SubscriptionController {

    private final ISubscriptionService iSubscriptionService;

    @PostMapping
    @SubscriptionControllerDocs.CreateSubscriptionDoc
    public ResponseEntity<EntityIdDto> createSubscription(@Validated @RequestBody SubscriptionDto subscriptionDto) {
        Subscription subscription = iSubscriptionService.createSubscription(subscriptionDto);
        return new ResponseEntity<>(EntityIdDto.of(subscription.getId()), HttpStatus.CREATED);
    }

    @GetMapping(RestConstants.ID_PATH)
    @SubscriptionControllerDocs.GetSubscriptionsDoc
    public SubscriptionDetailsDto getSubscriptionDetails(@PathVariable(name = RestConstants.ID) Long id) {
        return iSubscriptionService.getSubscriptionDetails(id);
    }
}
