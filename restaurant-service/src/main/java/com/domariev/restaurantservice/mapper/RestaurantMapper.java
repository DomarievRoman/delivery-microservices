package com.domariev.restaurantservice.mapper;

import com.domariev.restaurantservice.dto.RestaurantDto;
import com.domariev.restaurantservice.dto.RestaurantLocationDto;
import com.domariev.restaurantservice.model.Restaurant;
import com.domariev.restaurantservice.model.RestaurantLocation;
import com.domariev.restaurantservice.util.LocationParserUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RestaurantMapper {

    public RestaurantDto modelToDto(Restaurant restaurant) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Restaurant, RestaurantDto> typeMap = mapper.createTypeMap(Restaurant.class, RestaurantDto.class);
        Converter<RestaurantLocation, RestaurantLocationDto> locationConverter = context -> populateLocation(context.getSource());
        typeMap.addMappings(mapping -> mapping.using(locationConverter)
                .map(Restaurant::getLocation, RestaurantDto::setRestaurantLocationDto));
        return mapper.map(restaurant, RestaurantDto.class);
    }

    private static RestaurantLocationDto populateLocation(RestaurantLocation restaurantLocation) {
        RestaurantLocationDto restaurantLocationDto = new RestaurantLocationDto();
        Double latitude = Double.valueOf(restaurantLocation.getLatitude());
        Double longitude = Double.valueOf(restaurantLocation.getLongitude());
        restaurantLocationDto.setLatitude(latitude);
        restaurantLocationDto.setLongitude(longitude);
        String fullAddress = restaurantLocation.getFullAddress();
        if (Objects.nonNull(fullAddress)) {
            String[] addressParts = fullAddress.split(",\\s*");
            restaurantLocationDto.setFullAddress(fullAddress);
            restaurantLocationDto.setZipCode(restaurantLocation.getZipCode());
            restaurantLocationDto.setCity(LocationParserUtils.extractCity(addressParts));
            restaurantLocationDto.setState(LocationParserUtils.extractState(addressParts));
        } else {
            // service.findRestaurantLocationByCoordinates(restaurantLocationDto, latitude, longitude)
        }
        return restaurantLocationDto;
    }

}
