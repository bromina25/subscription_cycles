package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.dto.customer.CustomerDetailsDto;
import com.lufthansa.subscriptions.dto.customer.CustomerDto;
import com.lufthansa.subscriptions.dto.customer.CustomerInvoiceDto;
import com.lufthansa.subscriptions.entity.Customer;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    CustomerDetailsDto getCustomersDetails(Long id);

    CustomerInvoiceDto getCustomerInvoices(Long customerId, int page, int size);

    Customer findById(Long id, String entity);
}
