package com.domariev.restaurantservice.dto.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@SuperBuilder
@Getter
@Setter
public class GroupedMenuDto extends BaseMenuDto{

   private Map<String, List<MenuPositionDto>> groupedMenusByCategory;

}
