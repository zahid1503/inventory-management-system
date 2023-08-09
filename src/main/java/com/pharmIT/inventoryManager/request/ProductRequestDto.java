package com.pharmIT.inventoryManager.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.pharmIT.inventoryManager.utility.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ProductRequestDto {

    @NotEmpty(message = Constants.PRODUCT_NAME_NOT_FOUND)
    private String name;

    @Size(min = 10,max = 200,message = Constants.PRODUCT_DESCRIPTION_NOT_VALID)
    private String description;

    @Min(value = 1,message = Constants.PRODUCT_QUANTITY_NOT_VALID)
    @Max(value = 10,message = Constants.PRODUCT_QUANTITY_NOT_VALID)
    @NotNull(message = Constants.PRODUCT_QUANTITY_NOT_FOUND)
    private Integer quantity;

    @NotNull(message = Constants.PRODUCT_PRICE_NOT_VALID)
    private Double price;

    @NotNull(message = Constants.CATEGORY_ID_NOT_FOUND)
    @JsonProperty("category_id")
    private Long categoryId;
}
