package com.domariev.identityservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPaymentInfoDto {

    private UserDto userDto;
    private List<PaymentMethodDto> paymentMethodDtoList;

}
