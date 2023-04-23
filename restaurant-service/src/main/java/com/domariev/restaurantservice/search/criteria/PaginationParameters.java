package com.domariev.restaurantservice.search.criteria;

import lombok.Data;

@Data
public class PaginationParameters {

    private Integer page = 0;
    private Integer size = 50;
    private String sortDirection = "ASC";
    private String sortBy = "id";
}






