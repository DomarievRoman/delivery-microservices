package com.domariev.restaurantservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDto {

    private Long id;
    private Integer position;
    private String name;
    private Double score;
    private Integer ratingsCount;
    private String category;
    private String priceRange;
    private RestaurantLocationDto restaurantLocationDto;

}
