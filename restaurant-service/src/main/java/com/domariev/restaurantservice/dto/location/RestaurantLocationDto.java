package com.domariev.restaurantservice.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantLocationDto {

    private AddressDto addressDto;
    private CoordinateDto coordinateDto;

}
