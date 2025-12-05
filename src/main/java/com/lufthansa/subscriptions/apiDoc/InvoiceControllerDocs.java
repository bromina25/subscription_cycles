package com.lufthansa.subscriptions.apiDoc;

import com.lufthansa.subscriptions.dto.general.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class InvoiceControllerDocs {

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Operation(summary = "Register payment against an invoice.", responses = {
            @ApiResponse(responseCode = "200", description = "Payment of invoice is successfully created!"),
            @ApiResponse(responseCode = "400", description = "Error in setting subscription's data!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))})
    }, description = """
           For an invoice to register a payment, it must be in PENDING status. Once the payment
           is successfully persisted, the status of the invoice is updated based on the payment result.
           On SUCCESS: the invoice status becomes PAID. On FAILED: the invoice status becomes FAILED.
           The payment amount must match the invoice amount.
           """)
    public @interface RegisterInvoicePaymentDoc {
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all invoices.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Invoices retrieved successfully!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseError.class))
                    )
            },    description = """
                Retrieves all invoices in the system. The list is paginated and supports
                filter by customerId and status. Default order by id descending.
                """
    )
    public @interface GetInvoicesDoc {
    }
}
