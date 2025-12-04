package com.lufthansa.subscriptions.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @NotNull(message = "Name is required!")
    private String name;

    @NotNull(message = "Surname is required!")
    private String surname;

    @Email(message = "Email requires a valid format! eg: name@.com")
    @NotNull(message = "Email is required!")
    private String email;

    @NotNull(message = "Phone number is required!")
    private String phoneNumber;
}
