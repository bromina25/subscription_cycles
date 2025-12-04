package com.lufthansa.subscriptions.repository;

import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InvoiceRepository extends BaseRepository<Invoice> {

    @Query("""
                SELECT i FROM Invoice i
                JOIN i.subscription s
                WHERE s.customer.id = :customerId
            """)
    Page<Invoice> findInvoicesByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

    @Query("""
                SELECT i FROM Invoice i
                WHERE (:customerId IS NULL OR i.subscription.customer.id = :customerId)
                  AND (:status IS NULL OR i.invoiceStatus = :status)
                  AND (:dateFrom IS NULL OR i.periodStart >= :dateFrom)
                  AND (:dateTo IS NULL OR i.periodEnd <= :dateTo)
            """)
    Page<Invoice> findInvoices(@Param("customerId") Long customerId, @Param("status") InvoiceStatus status, @Param("dateFrom") LocalDate dateFrom,
                               @Param("dateTo") LocalDate dateTo, Pageable pageable);
}
