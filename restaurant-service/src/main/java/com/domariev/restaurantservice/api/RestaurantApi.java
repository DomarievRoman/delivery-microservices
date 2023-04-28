package com.domariev.restaurantservice.api;

import com.domariev.restaurantservice.dto.menu.FilteredRestaurantDto;
import com.domariev.restaurantservice.dto.restaurant.RestaurantDto;
import com.domariev.restaurantservice.search.criteria.PaginationParameters;
import com.domariev.restaurantservice.search.criteria.RestaurantSearchCriteria;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface RestaurantApi {

    @GetMapping(value = "/id/{id}", produces = {"application/json"})
    ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Integer id);

    @GetMapping(produces = {"application/json"})
    ResponseEntity<FilteredRestaurantDto> searchRestaurantsByKeyword(@ParameterObject RestaurantSearchCriteria restaurantSearchCriteria,
                                                                     @ParameterObject PaginationParameters paginationParameters);

    @GetMapping(value = "/all", produces = {"application/json"})
    ResponseEntity<FilteredRestaurantDto> findAllRestaurants(@RequestParam(defaultValue = "100") Integer size,
                                                             @RequestParam(defaultValue = "0") Integer page);
}
