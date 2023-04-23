package com.domariev.restaurantservice.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class LocationParserUtils {

    public String extractCity(String[] splitAddress) {
        return Arrays.stream(splitAddress)
                .skip(1)
                .limit(1)
                .findFirst()
                .orElse(null);
    }

    public String extractState(String[] splitAddress) {
        return Arrays.stream(splitAddress)
                .filter(part -> part.matches("[A-Z]{2}"))
                .findFirst()
                .orElse(null);
    }

}
