package com.domariev.restaurantservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
public class MenuDto extends BaseMenuDto {

    private List<MenuPositionDto> menuPositionDtoList;

}
