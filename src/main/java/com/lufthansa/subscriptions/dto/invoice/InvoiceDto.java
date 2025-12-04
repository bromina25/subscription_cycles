package com.lufthansa.subscriptions.dto.invoice;

import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.dto.subscription.SubscriptionDetailsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

    private Long id;

    private SubscriptionDetailsDto subscription;

    private LocalDate periodStart;

    private LocalDate periodEnd;

    private Double amount;

    private InvoiceStatus invoiceStatus;

    private LocalDate dueDate;
}
