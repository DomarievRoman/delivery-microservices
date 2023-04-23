package com.domariev.restaurantservice.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RestaurantLocation {

    private String fullAddress;

    private String zipCode;

    private String latitude;

    private String longitude;

}
