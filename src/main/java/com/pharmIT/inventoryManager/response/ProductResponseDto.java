package com.pharmIT.inventoryManager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id ;

    private String name;

    private String description;

    private Integer quantity;

    private Double price;

    @JsonProperty("category_id")
    private Long categoryId;
}
