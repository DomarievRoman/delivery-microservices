package com.domariev.locationservice.service;

import com.domariev.locationservice.dto.AddressDto;
import com.domariev.locationservice.dto.CoordinateDto;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.domariev.locationservice.constants.CommonConstants.GOOGLE_MAPS_API_KEY;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final GeoCodingResultParserService geoCodingResultParserService;

    @SneakyThrows
    public AddressDto getAddressByCoordinates(CoordinateDto coordinateDto) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(System.getenv(GOOGLE_MAPS_API_KEY))
                .build();

        Double latitude = coordinateDto.getLatitude();
        Double longitude = coordinateDto.getLongitude();
        GeocodingResult[] geocodingResultList = GeocodingApi
                .reverseGeocode(context, new LatLng(latitude, longitude))
                .await();

        GeocodingResult geocodingResult = Arrays.stream(geocodingResultList)
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException(
                        String.format("Invalid request, wrong coordinates (%s, %s)", latitude, longitude)));

        return geoCodingResultParserService.parseToAddressDto(geocodingResult);
    }

}
