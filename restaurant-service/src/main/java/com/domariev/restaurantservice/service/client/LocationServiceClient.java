package com.domariev.restaurantservice.service.client;

import com.domariev.restaurantservice.dto.location.AddressDto;
import com.domariev.restaurantservice.dto.location.CoordinateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "location-service")
public interface LocationServiceClient {

    @GetMapping("/location/restaurant/coordinates")
    ResponseEntity<AddressDto> findRestaurantAddressByCoordinates(@SpringQueryMap CoordinateDto coordinateDto);

}
