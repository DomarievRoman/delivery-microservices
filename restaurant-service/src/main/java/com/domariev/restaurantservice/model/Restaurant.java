package com.domariev.restaurantservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant extends BaseRestaurant {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fullAddress", column = @Column(name = "full_address")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "latitude", column = @Column(name = "lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "lng"))
    })
    private RestaurantLocation location;
}
