package com.domariev.restaurantservice.controller;

import com.domariev.restaurantservice.api.RestaurantApi;
import com.domariev.restaurantservice.dto.menu.FilteredRestaurantDto;
import com.domariev.restaurantservice.dto.restaurant.RestaurantDto;
import com.domariev.restaurantservice.search.criteria.PaginationParameters;
import com.domariev.restaurantservice.search.criteria.RestaurantSearchCriteria;
import com.domariev.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantApi {

    private final RestaurantService restaurantService;

    @Override
    public ResponseEntity<RestaurantDto> getRestaurantById(Integer id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }

    @Override
    public ResponseEntity<FilteredRestaurantDto> searchRestaurantsByKeyword(@Valid RestaurantSearchCriteria restaurantSearchCriteria,
                                                                            PaginationParameters paginationParameters) {
        FilteredRestaurantDto filteredRestaurants = restaurantService.searchByKeyword(restaurantSearchCriteria, paginationParameters);
        return filteredRestaurants.restaurants().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(filteredRestaurants, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FilteredRestaurantDto> findAllRestaurants(Integer size, Integer page) {
        FilteredRestaurantDto filteredRestaurants = restaurantService.getAll(page, size);
        return filteredRestaurants.restaurants().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(filteredRestaurants, HttpStatus.OK);
    }

}
