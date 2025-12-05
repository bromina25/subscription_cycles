package com.lufthansa.subscriptions.service.serviceImpl;

import com.lufthansa.subscriptions.constant.enums.ActivationState;
import com.lufthansa.subscriptions.constant.enums.BillingPeriod;
import com.lufthansa.subscriptions.constant.enums.SubscriptionStatus;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDto;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDetailsDto;
import com.lufthansa.subscriptions.entity.Customer;
import com.lufthansa.subscriptions.entity.Subscription;
import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import com.lufthansa.subscriptions.exception.BadRequestException;
import com.lufthansa.subscriptions.repository.SubscriptionRepository;
import com.lufthansa.subscriptions.service.*;
import com.lufthansa.subscriptions.util.JsonUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionServiceImpl extends BaseService<Subscription> implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerService customerService;
    private final SubscriptionPlanService subscriptionPlanService;
    private final InvoiceService invoiceService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   CustomerService customerService,
                                   SubscriptionPlanServiceImpl subscriptionPlanService,
                                   InvoiceService invoiceService) {
        super(subscriptionRepository);
        this.subscriptionRepository = subscriptionRepository;
        this.customerService = customerService;
        this.subscriptionPlanService = subscriptionPlanService;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<Subscription> findByStatusAndCurrentPeriodEndBefore(SubscriptionStatus status, LocalDate date) {
        return subscriptionRepository.findByStatusAndCurrentPeriodEndBefore(status, date);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Subscription createSubscription(SubscriptionDto subscriptionDto) {

        Customer customer = customerService.findById(subscriptionDto.getCustomer().getId(),
                Customer.class.getSimpleName());

        SubscriptionPlan plan = subscriptionPlanService.findById(subscriptionDto.getPlan().getId(),
                SubscriptionPlan.class.getSimpleName());

        validateSubscription(customer, plan);
        Subscription subscription = save(buildSubscription(customer, plan));
        invoiceService.generateInvoice(subscription);
        return subscription;
    }

    private void validateSubscription(Customer customer, SubscriptionPlan subscriptionPlan) {
        validateCustomerActive(customer);
        validatePlanActive(subscriptionPlan);
        validateNoOtherActiveSubscription(customer, subscriptionPlan);
    }

    private void validateCustomerActive(Customer customer) {
        if (customer.getActivationState() == ActivationState.INACTIVE) {
            throw new BadRequestException(
                    String.format("Customer %s %s is inactive! Please choose another one.",
                            customer.getName(), customer.getSurname())
            );
        }
    }

    private void validatePlanActive(SubscriptionPlan subscriptionPlan) {
        if (subscriptionPlan.getActivationState() == ActivationState.INACTIVE) {
            throw new BadRequestException(
                    String.format("Plan '%s' is inactive! Please choose another one.", subscriptionPlan.getName())
            );
        }
    }

    private void validateNoOtherActiveSubscription(Customer customer, SubscriptionPlan subscriptionPlan) {
        boolean exists = subscriptionRepository.existsOtherActiveSubscription(
                customer.getId(), subscriptionPlan.getId());

        if (exists) {
            throw new BadRequestException(
                    String.format("Customer %s %s already has an active subscription for the plan '%s'.",
                            customer.getName(), customer.getSurname(), subscriptionPlan.getName())
            );
        }
    }

    private Subscription buildSubscription(Customer customer, SubscriptionPlan plan) {
        LocalDate today = LocalDate.now();
        return Subscription.builder()
                .customer(customer)
                .plan(plan)
                .startDate(today)
                .status(SubscriptionStatus.ACTIVE)
                .currentPeriodStart(today)
                .currentPeriodEnd(calculateCurrentPeriodEnd(today, plan.getBillingPeriod()))
                .build();
    }

    @Override
    public LocalDate calculateCurrentPeriodEnd(LocalDate startDate, BillingPeriod billingPeriod) {
        return switch (billingPeriod) {
            case MONTHLY -> startDate.plusMonths(1);
            case YEARLY -> startDate.plusYears(1);
        };
    }

    @Override
    public SubscriptionDetailsDto getSubscriptionDetails(Long id) {
        Subscription subscription = findById(id, Subscription.class.getSimpleName());
        return JsonUtil.map(subscription, SubscriptionDetailsDto.class);
    }
}