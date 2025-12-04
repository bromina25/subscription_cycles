package com.lufthansa.subscriptions.repository;

import com.lufthansa.subscriptions.entity.SubscriptionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPlanRepository extends BaseRepository<SubscriptionPlan> {

    @Query(
            value = "SELECT * FROM subscription_plan sp " +
                    "WHERE (:name IS NULL OR sp.name ILIKE %:name%)",
            nativeQuery = true)
    Page<SubscriptionPlan> findAll(@Param("name") String name, Pageable pageable);
}
