package com.domariev.restaurantservice.service;

import com.domariev.restaurantservice.dto.FilteredRestaurantDto;
import com.domariev.restaurantservice.dto.PriceRange;
import com.domariev.restaurantservice.dto.RestaurantDto;
import com.domariev.restaurantservice.exception.RestaurantNotFoundException;
import com.domariev.restaurantservice.mapper.RestaurantMapper;
import com.domariev.restaurantservice.model.Restaurant;
import com.domariev.restaurantservice.repository.RestaurantRepository;
import com.domariev.restaurantservice.search.criteria.PaginationParameters;
import com.domariev.restaurantservice.search.criteria.RestaurantSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantDto getById(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant with id '%s' not found", id)));
        return restaurantMapper.modelToDto(restaurant);
    }

    public FilteredRestaurantDto getAll(Integer page, Integer size) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(PageRequest.of(page, size));
        return createFilteredResult(restaurants);
    }

    public FilteredRestaurantDto searchByKeyword(RestaurantSearchCriteria searchCriteria,
                                                 PaginationParameters paginationParameters) {
        PageRequest pageRequest = PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize(),
                Sort.Direction.fromString(paginationParameters.getSortDirection()), paginationParameters.getSortBy());

        Page<Restaurant> restaurants = restaurantRepository.findByCriteria(searchCriteria.getUserLocation(),
                searchCriteria.getCategory(), searchCriteria.getName(), PriceRange.getPriceRange(searchCriteria),
                searchCriteria.getScore(), pageRequest);

        return createFilteredResult(restaurants);
    }

    private FilteredRestaurantDto createFilteredResult(Page<Restaurant> pageableRestaurants) {
        List<RestaurantDto> restaurantsDto = pageableRestaurants.stream()
                .map(restaurantMapper::modelToDto)
                .toList();

        return new FilteredRestaurantDto(
                restaurantsDto,
                pageableRestaurants.getPageable().getPageNumber(),
                pageableRestaurants.getTotalPages(),
                pageableRestaurants.getSize(),
                pageableRestaurants.getTotalElements());
    }

}
