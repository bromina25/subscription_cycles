package com.lufthansa.subscriptions.controller;

import com.lufthansa.subscriptions.apiDoc.InvoiceControllerDocs;
import com.lufthansa.subscriptions.constant.RestConstants;
import com.lufthansa.subscriptions.constant.enums.InvoiceStatus;
import com.lufthansa.subscriptions.dto.general.CustomPage;
import com.lufthansa.subscriptions.dto.general.EntityIdDto;
import com.lufthansa.subscriptions.dto.invoice.InvoiceDto;
import com.lufthansa.subscriptions.dto.payment.PaymentRegistrationDto;
import com.lufthansa.subscriptions.entity.Invoice;
import com.lufthansa.subscriptions.service.interfaces.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.lufthansa.subscriptions.constant.RestConstants.*;
import static com.lufthansa.subscriptions.constant.RestConstants.DEFAULT_PAGE_SIZE;
import static com.lufthansa.subscriptions.constant.RestConstants.SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.InvoiceController.BASE_PATH)
public class InvoiceController {

    private final IInvoiceService iInvoiceService;

    @PostMapping(RestConstants.InvoiceController.INVOICE_PAYMENT)
    @InvoiceControllerDocs.RegisterInvoicePaymentDoc
    public ResponseEntity<EntityIdDto> registerInvoicePayment(@PathVariable(name = ID) Long invoiceId,
                                                              @Validated @RequestBody PaymentRegistrationDto paymentRegistrationDto) {
        Invoice invoice = iInvoiceService.registerInvoicePayment(invoiceId, paymentRegistrationDto);
        return new ResponseEntity<>(EntityIdDto.of(invoice.getId()), HttpStatus.CREATED);
    }

    @GetMapping(RestConstants.InvoiceController.INVOICE_ADMIN)
    @InvoiceControllerDocs.GetInvoicesDoc
    public ResponseEntity<CustomPage<InvoiceDto>> getInvoices(@RequestParam(required = false) Long customerId,
                                                              @RequestParam(required = false) InvoiceStatus status,
                                                              @RequestParam(value = PAGE, defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                              @RequestParam(value = SIZE, defaultValue = DEFAULT_PAGE_SIZE) int size) {
        Page<InvoiceDto> invoices = iInvoiceService.getInvoices(customerId, status, page, size);
        return ResponseEntity.ok(new CustomPage<>(invoices));
    }
}
