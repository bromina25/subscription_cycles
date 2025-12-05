package com.lufthansa.subscriptions.service.interfaces;

import com.lufthansa.subscriptions.dto.customer.CustomerDetailsDto;
import com.lufthansa.subscriptions.dto.customer.CustomerDto;
import com.lufthansa.subscriptions.dto.customer.CustomerInvoiceDto;
import com.lufthansa.subscriptions.entity.Customer;

public interface ICustomerService {

    Customer createCustomer(CustomerDto customerDto);

    CustomerDetailsDto getCustomersDetails(Long id);

    CustomerInvoiceDto getCustomerInvoices(Long customerId, int page, int size);

    Customer findById(Long id, String entity);
}
