package com.domariev.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantLocationDto {

    private String city;
    private String state;
    private String fullAddress;
    private String zipCode;
    private Double latitude;
    private Double longitude;

}
