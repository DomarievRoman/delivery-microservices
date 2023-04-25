package com.domariev.restaurantservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menus")
@Getter
@Setter
public class MenuPosition {

    @Id
    private Integer id;

    private String category;

    private String name;

    private String description;

    private String price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}
