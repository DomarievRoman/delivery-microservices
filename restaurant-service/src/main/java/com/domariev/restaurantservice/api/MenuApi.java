package com.domariev.restaurantservice.api;

import com.domariev.restaurantservice.dto.menu.BaseMenuDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MenuApi {

    @GetMapping(value = "/restaurant", produces = {"application/json"})
    ResponseEntity<BaseMenuDto> getMenuByRestaurantId(@RequestParam(name = "id") Integer restaurantId);

    @GetMapping(value = "/grouped/restaurant", produces = {"application/json"})
    ResponseEntity<BaseMenuDto> getMenuByRestaurantIdGroupedByCategory(@RequestParam(name = "id") Integer restaurantId);

}
