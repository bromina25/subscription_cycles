package com.lufthansa.subscriptions.controller;

import com.lufthansa.subscriptions.dto.customer.CustomerDto;
import com.lufthansa.subscriptions.dto.customer.CustomerDetailsDto;
import com.lufthansa.subscriptions.dto.customer.CustomerInvoiceDto;
import com.lufthansa.subscriptions.dto.general.EntityIdDto;
import com.lufthansa.subscriptions.entity.Customer;
import com.lufthansa.subscriptions.apiDoc.CustomerControllerDocs;
import com.lufthansa.subscriptions.constant.RestConstants;
import com.lufthansa.subscriptions.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.lufthansa.subscriptions.constant.RestConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.CustomerController.BASE_PATH)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @CustomerControllerDocs.CreateCustomerDoc
    public ResponseEntity<EntityIdDto> createCustomer(@Validated @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(EntityIdDto.of(customer.getId()), HttpStatus.CREATED);
    }

    @GetMapping(RestConstants.ID_PATH)
    @CustomerControllerDocs.GetCustomerDetailsDoc
    public CustomerDetailsDto getCustomersDetails(@PathVariable(name = RestConstants.ID) Long id) {
        return customerService.getCustomersDetails(id);
    }

    @GetMapping(RestConstants.CustomerController.CUSTOMER_INVOICE)
    @CustomerControllerDocs.GetCustomerInvoicesDoc
    public CustomerInvoiceDto getCustomerInvoices(@PathVariable(name = ID) Long customerId,
                                                  @RequestParam(value = PAGE, defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(value = SIZE, defaultValue = DEFAULT_PAGE_SIZE) int size) {
        return customerService.getCustomerInvoices(customerId, page, size);
    }
}
