package com.domariev.identityservice.dto;

import lombok.Data;

@Data
public class PaymentMethodDto {

    private PaymentCardDto paymentCardDto;
    private boolean isActive;

}
