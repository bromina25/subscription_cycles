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

public class SubscriptionControllerDocs {

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Operation(summary = "Create subscription.", responses = {
            @ApiResponse(responseCode = "200", description = "Subscription is successfully created!"),
            @ApiResponse(responseCode = "400", description = "Error in setting subscription's data!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))})
    }, description = """
            Create a new subscription. With the creation of a subscription, an invoice is also generated.
            Upon creation, status of subscription is ACTIVE and status of invoice is PENDING. Amount
            of invoice is calculated based on pricePerMonth and billingPeriod. The plan and the customer of
            subscription have to be active. Customer can have only on active subscription for a specific plan.
            """)
    public @interface CreateSubscriptionDoc {
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get subscription.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Subscriptions retrieved successfully!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))
            )
            },    description = """
                Retrieves all subscriptions in the system. The list is paginated and supports
                filter by name and default order by id descending.
                """
    )
    public @interface GetSubscriptionsDoc {
    }
}
