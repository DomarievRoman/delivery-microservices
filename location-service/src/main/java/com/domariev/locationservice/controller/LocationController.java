package com.domariev.locationservice.controller;

import com.domariev.locationservice.api.LocationApi;
import com.domariev.locationservice.dto.AddressDto;
import com.domariev.locationservice.dto.CoordinateDto;
import com.domariev.locationservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController implements LocationApi {

    private final LocationService locationService;

    @Override
    public ResponseEntity<AddressDto> findRestaurantAddressByCoordinates(CoordinateDto coordinateDto) {
        return new ResponseEntity<>(locationService.getAddressByCoordinates(coordinateDto), HttpStatus.OK);
    }

}
