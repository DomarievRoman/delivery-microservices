package com.domariev.locationservice.api;

import com.domariev.locationservice.dto.AddressDto;
import com.domariev.locationservice.dto.CoordinateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface LocationApi {

    @GetMapping(value = "/restaurant/coordinates", produces = {"application/json"})
    ResponseEntity<AddressDto> findRestaurantAddressByCoordinates(CoordinateDto coordinateDto);

}
