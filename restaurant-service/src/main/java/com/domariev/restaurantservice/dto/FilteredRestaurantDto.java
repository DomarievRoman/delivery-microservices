package com.domariev.restaurantservice.dto;

import java.util.List;

public record FilteredRestaurantDto(List<RestaurantDto> restaurants, Integer page, Integer totalPages, Integer size, Long totalCount) {

}
