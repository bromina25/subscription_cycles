package com.lufthansa.subscriptions.service.serviceImpl;

import com.lufthansa.subscriptions.constant.enums.ActivationState;
import com.lufthansa.subscriptions.dto.customer.CustomerDto;
import com.lufthansa.subscriptions.dto.customer.CustomerDetailsDto;
import com.lufthansa.subscriptions.dto.customer.CustomerInvoiceDto;
import com.lufthansa.subscriptions.dto.general.CustomPage;
import com.lufthansa.subscriptions.dto.invoice.InvoiceDto;
import com.lufthansa.subscriptions.entity.Customer;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.repository.CustomerRepository;
import com.lufthansa.subscriptions.service.CustomerService;
import com.lufthansa.subscriptions.service.InvoiceService;
import com.lufthansa.subscriptions.util.JsonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {

    private final InvoiceService invoiceService;

    public CustomerServiceImpl(CustomerRepository customerRepository, InvoiceService invoiceService) {
        super(customerRepository);
        this.invoiceService = invoiceService;
    }

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        return save(buildCustomer(customerDto));
    }

    private Customer buildCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .activationState(ActivationState.ACTIVE)
                .build();
    }

    @Override
    public CustomerDetailsDto getCustomersDetails(Long id) {
        Customer customer = findById(id, Customer.class.getSimpleName());
        return JsonUtil.map(customer, CustomerDetailsDto.class);
    }

    @Override
    public CustomerInvoiceDto getCustomerInvoices(Long customerId, int page, int size) {
        Customer customer = findById(customerId, Customer.class.getSimpleName());
        CustomerDetailsDto customerDetailsDto = JsonUtil.map(customer, CustomerDetailsDto.class);

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Invoice> invoicePage = invoiceService.findInvoicesByCustomerId(customerId, pageable);
        Page<InvoiceDto> invoiceDtoPage = invoicePage.map(invoice -> JsonUtil.map(invoice, InvoiceDto.class));

        return CustomerInvoiceDto.build(customerDetailsDto, new CustomPage<>(invoiceDtoPage));
    }
}
