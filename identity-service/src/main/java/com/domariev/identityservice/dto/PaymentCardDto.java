package com.domariev.identityservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCardDto {

    @Pattern(regexp = "^\\d{16,18}$", message = "Invalid card number")
    private String number;
    @Pattern(regexp = "^(0[1-9]|1[0-2]|[1-9])/(1[8-9]|[2-9][0-9])$", message = "Invalid expiration date")
    private String expirationDate;
    @Pattern(regexp = "^[0-9]{3,4}$", message = "Invalid cvv code")
    private String cvvCode;

}
