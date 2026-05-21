package com.liverpool.mx.product.models;

import lombok.Data;

@Data
public class ProductResponseDto {

    private String id;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private String category;
    private boolean active;
    private Integer stock;
    private String inventoryStatus;

}
