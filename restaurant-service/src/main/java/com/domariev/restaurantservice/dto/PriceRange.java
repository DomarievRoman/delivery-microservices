package com.domariev.restaurantservice.dto;

import com.domariev.restaurantservice.search.criteria.RestaurantSearchCriteria;

import java.util.Objects;

public enum PriceRange {

    INEXPENSIVE("$"),
    MODERATE("$$"),
    EXPENSIVE("$$$"),
    VERY_EXPENSIVE("$$$$");

    final String value;

    PriceRange(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getPriceRange(RestaurantSearchCriteria searchCriteria) {
        PriceRange priceRange = searchCriteria.getPriceRange();
        return Objects.nonNull(priceRange) ? priceRange.getValue() : null;
    }
}
