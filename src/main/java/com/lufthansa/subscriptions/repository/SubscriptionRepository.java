package com.lufthansa.subscriptions.repository;

import com.lufthansa.subscriptions.constant.enums.SubscriptionStatus;
import com.lufthansa.subscriptions.entity.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends BaseRepository<Subscription> {

    @Query("""
                SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
                FROM Subscription s
                WHERE s.customer.id = :customerId
                  AND s.plan.id = :planId
                  AND s.status = 'ACTIVE'
            """)
    boolean existsOtherActiveSubscription(@Param("customerId") Long customerId, @Param("planId") Long planId
    );

    List<Subscription> findByStatusAndCurrentPeriodEndBefore(SubscriptionStatus status,
                                                             LocalDate date);

}
