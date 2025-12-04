package com.lufthansa.subscriptions.dto.customer;

import com.lufthansa.subscriptions.constant.enums.ActivationState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDetailsDto extends CustomerDto {

    private ActivationState activationState;
}
