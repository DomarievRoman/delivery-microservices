package com.domariev.restaurantservice.dto.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public abstract class BaseMenuDto {

    private Integer restaurantId;
    private String restaurantName;
    private Integer positionsCount;

}
