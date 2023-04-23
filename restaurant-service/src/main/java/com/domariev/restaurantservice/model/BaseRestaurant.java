package com.domariev.restaurantservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseRestaurant {

    @Id
    private Integer id;

    private Integer position;

    private String name;

    private Double score;

    @Column(name = "ratings")
    private Integer ratingsCount;

    private String category;

    private String priceRange;

}

