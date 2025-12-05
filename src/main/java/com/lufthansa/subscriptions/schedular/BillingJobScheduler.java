package com.lufthansa.subscriptions.schedular;

import com.lufthansa.subscriptions.service.interfaces.IBillingJobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@AllArgsConstructor
public class BillingJobScheduler {

    private final IBillingJobService billingJobService;

    @Scheduled(cron = "0 0 0 * * ?")  // runs every day at 00:00
    public void runBillingCycleJob() {
        int renewed = billingJobService.processBillingCycle();
        log.info("Billing cycle executed at 00:00. Renewed subscriptions: {}", renewed);
    }
}
