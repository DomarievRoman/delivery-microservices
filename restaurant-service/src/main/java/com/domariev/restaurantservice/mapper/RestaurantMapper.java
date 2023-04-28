package com.domariev.restaurantservice.mapper;

import com.domariev.restaurantservice.dto.location.AddressDto;
import com.domariev.restaurantservice.dto.location.CoordinateDto;
import com.domariev.restaurantservice.dto.restaurant.RestaurantDto;
import com.domariev.restaurantservice.dto.location.RestaurantLocationDto;
import com.domariev.restaurantservice.model.Restaurant;
import com.domariev.restaurantservice.model.RestaurantLocation;
import com.domariev.restaurantservice.service.client.LocationServiceClient;
import com.domariev.restaurantservice.util.LocationParserUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    private final LocationServiceClient locationServiceClient;

    public RestaurantDto modelToDto(Restaurant restaurant) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Restaurant, RestaurantDto> typeMap = mapper.createTypeMap(Restaurant.class, RestaurantDto.class);
        Converter<RestaurantLocation, RestaurantLocationDto> locationConverter = context -> populateLocation(context.getSource());
        typeMap.addMappings(mapping -> mapping.using(locationConverter)
                .map(Restaurant::getLocation, RestaurantDto::setRestaurantLocationDto));
        return mapper.map(restaurant, RestaurantDto.class);
    }

    private RestaurantLocationDto populateLocation(RestaurantLocation restaurantLocation) {
        CoordinateDto coordinateDto = getCoordinates(restaurantLocation);
        AddressDto addressDto = Objects.nonNull(restaurantLocation.getFullAddress()) ?
                getAddress(restaurantLocation) :
                locationServiceClient.findRestaurantAddressByCoordinates(coordinateDto).getBody();

        return RestaurantLocationDto.builder()
                .addressDto(addressDto)
                .coordinateDto(coordinateDto)
                .build();
    }

    private static CoordinateDto getCoordinates(RestaurantLocation restaurantLocation) {
        Double latitude = Double.valueOf(restaurantLocation.getLatitude());
        Double longitude = Double.valueOf(restaurantLocation.getLongitude());
        return CoordinateDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

    private static AddressDto getAddress(RestaurantLocation restaurantLocation) {
        String fullAddress = restaurantLocation.getFullAddress();
        String[] addressParts = fullAddress.split(",\\s*");
        return AddressDto.builder()
                .fullAddress(fullAddress)
                .zipCode(restaurantLocation.getZipCode())
                .city(LocationParserUtils.extractCity(addressParts))
                .state(LocationParserUtils.extractState(addressParts))
                .build();
    }

}
