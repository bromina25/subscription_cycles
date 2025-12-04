package com.lufthansa.subscriptions.entity;

import com.lufthansa.subscriptions.constant.enums.BillingPeriod;
import com.lufthansa.subscriptions.constant.enums.ActivationState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double pricePerMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingPeriod billingPeriod;

    @Column(nullable = false)
    private Integer maxUsers;

    @Enumerated(EnumType.STRING)
    @Column(name = "activation_state")
    private ActivationState activationState;
}
