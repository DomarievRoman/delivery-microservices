package com.domariev.restaurantservice.repository;

import com.domariev.restaurantservice.model.MenuPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuPosition, Integer> {

    List<MenuPosition> findByRestaurantId(Integer id);

}
