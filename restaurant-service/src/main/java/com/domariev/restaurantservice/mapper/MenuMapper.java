package com.domariev.restaurantservice.mapper;

import com.domariev.restaurantservice.dto.menu.MenuPositionDto;
import com.domariev.restaurantservice.model.MenuPosition;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {

    public MenuPositionDto modelToDto(MenuPosition menuPosition) {
        return new ModelMapper().map(menuPosition, MenuPositionDto.class);
    }

}
