package com.domariev.restaurantservice.repository;

import com.domariev.restaurantservice.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE " +
            "r.location.fullAddress LIKE %:fullAddress% " +
            "AND (:category IS NULL OR LOWER(r.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:priceRange IS NULL OR r.priceRange = :priceRange) " +
            "AND (:score IS NULL OR r.score >= :score)")
    Page<Restaurant> findByCriteria(String fullAddress, String category, String name, String priceRange, Double score, Pageable pageable);

}
