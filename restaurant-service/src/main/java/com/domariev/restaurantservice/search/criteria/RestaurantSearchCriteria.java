package com.domariev.restaurantservice.search.criteria;

import com.domariev.restaurantservice.dto.menu.PriceRange;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantSearchCriteria {

    private String category;
    private String name;
    private PriceRange priceRange;
    @NotNull(message = "User location cannot be null")
    private String userLocation;
    private Double score;

}
