package com.domariev.locationservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String city;
    private String state;
    private String fullAddress;
    private String zipCode;

}
