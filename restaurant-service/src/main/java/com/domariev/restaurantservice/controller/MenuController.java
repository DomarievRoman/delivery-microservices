package com.domariev.restaurantservice.controller;

import com.domariev.restaurantservice.api.MenuApi;
import com.domariev.restaurantservice.dto.menu.BaseMenuDto;
import com.domariev.restaurantservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController implements MenuApi {

    private final MenuService menuService;

    @Override
    public ResponseEntity<BaseMenuDto> getMenuByRestaurantId(Integer restaurantId) {
        return new ResponseEntity<>(menuService.getMenuByRestaurant(restaurantId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseMenuDto> getMenuByRestaurantIdGroupedByCategory(Integer restaurantId) {
        return new ResponseEntity<>(menuService.getMenuByRestaurantGroupedByCategory(restaurantId), HttpStatus.OK);
    }

}
