package com.lufthansa.subscriptions.dto.customer;

import com.lufthansa.subscriptions.dto.general.CustomPage;
import com.lufthansa.subscriptions.dto.invoice.InvoiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerInvoiceDto {

    private CustomerDetailsDto customer;

    private CustomPage<InvoiceDto> invoices;

    public static CustomerInvoiceDto build(CustomerDetailsDto customerDetailsDto,
                                           CustomPage<InvoiceDto> invoiceDtoCustomPage) {
        CustomerInvoiceDto dto = new CustomerInvoiceDto();
        dto.setCustomer(customerDetailsDto);
        dto.setInvoices(invoiceDtoCustomPage);
        return dto;
    }
}
