package com.domariev.restaurantservice.dto.menu;

import com.domariev.restaurantservice.dto.restaurant.RestaurantDto;

import java.util.List;

public record FilteredRestaurantDto(List<RestaurantDto> restaurants, Integer page, Integer totalPages, Integer size, Long totalCount) {

}
