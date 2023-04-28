package com.domariev.restaurantservice.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    private String city;
    private String state;
    private String fullAddress;
    private String zipCode;

}
