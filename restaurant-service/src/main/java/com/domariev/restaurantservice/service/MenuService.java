package com.domariev.restaurantservice.service;

import com.domariev.restaurantservice.dto.menu.BaseMenuDto;
import com.domariev.restaurantservice.dto.menu.GroupedMenuDto;
import com.domariev.restaurantservice.dto.menu.MenuDto;
import com.domariev.restaurantservice.dto.menu.MenuPositionDto;
import com.domariev.restaurantservice.dto.restaurant.RestaurantDto;
import com.domariev.restaurantservice.mapper.MenuMapper;
import com.domariev.restaurantservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;
    private final MenuMapper menuMapper;

    public BaseMenuDto getMenuByRestaurant(Integer restaurantId) {
        List<MenuPositionDto> menuPositionDtoList = getMenuPositionsByRestaurantId(restaurantId);
        RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
        return MenuDto.builder()
                .restaurantId(restaurantId)
                .restaurantName(restaurantDto.getName())
                .positionsCount(menuPositionDtoList.size())
                .menuPositionDtoList(menuPositionDtoList)
                .build();
    }

    public BaseMenuDto getMenuByRestaurantGroupedByCategory(Integer restaurantId) {
        List<MenuPositionDto> menuPositionDtoList = getMenuPositionsByRestaurantId(restaurantId);
        Map<String, List<MenuPositionDto>> groupedMenuByCategory = menuPositionDtoList.stream()
                .collect(Collectors.groupingBy(MenuPositionDto::getCategory));
        RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
        return GroupedMenuDto.builder()
                .restaurantId(restaurantId)
                .restaurantName(restaurantDto.getName())
                .positionsCount(menuPositionDtoList.size())
                .groupedMenusByCategory(groupedMenuByCategory)
                .build();
    }

    private List<MenuPositionDto> getMenuPositionsByRestaurantId(Integer restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId).stream()
                .map(menuMapper::modelToDto)
                .toList();
    }

}
