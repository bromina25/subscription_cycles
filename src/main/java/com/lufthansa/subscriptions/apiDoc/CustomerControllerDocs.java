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

public class CustomerControllerDocs {

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Operation(summary = "Create customer.", responses = {
            @ApiResponse(responseCode = "200", description = "Customer is successfully created!"),
            @ApiResponse(responseCode = "400", description = "Error in setting customer's data!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))})
    }, description = """
            Create a new ACTIVE customer.
            """)
    public @interface CreateCustomerDoc {
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get customer details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Details of customer retrieved successfully!"),
                    @ApiResponse(responseCode = "404", description = "Id of customer not found!",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseError.class))
                    ), @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))
            )
            }, description = """
            Retrieve details of customer by customer id.
            """
    )
    public @interface GetCustomerDetailsDoc {
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all invoices of customer.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Details of customer's invoices retrieved successfully!"),
                    @ApiResponse(responseCode = "404", description = "Id of customer not found!",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseError.class))
                    ), @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))
            )
            }, description = """
            Retrieve details of customer and customer's invoices by customer id.
            """
    )
    public @interface GetCustomerInvoicesDoc {
    }
}
