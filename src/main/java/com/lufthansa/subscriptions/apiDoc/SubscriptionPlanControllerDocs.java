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

public class SubscriptionPlanControllerDocs {

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Operation(summary = "Create subscription plan.", responses = {
            @ApiResponse(responseCode = "200", description = "Subscription plan is successfully created!"),
            @ApiResponse(responseCode = "400", description = "Error in setting subscription plan's data!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))})
    }, description = """
            Users with the ADMIN role can create a new subscription plan.
            """)
    public @interface CreateSubscriptionPlanDoc {
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get subscription plans with optional filters.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of subscription plans retrieved successfully!"),
                    @ApiResponse(responseCode = "400", description = "Invalid filter parameters!",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseError.class))
                    ), @ApiResponse(responseCode = "500", description = "Internal server error!",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseError.class))
            )
            },    description = """
                Retrieve a paginated list of subscription plans.
                Optional query parameters name, surname and activationState can be used to filter results.
                Sorted by id desc.
                """
    )
    public @interface GetSubscriptionPlanDoc {
    }
}
