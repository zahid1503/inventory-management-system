package com.pharmIT.inventoryManager.request;

import com.pharmIT.inventoryManager.utility.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotEmpty(message = Constants.CATEGORY_NAME_NOT_FOUND)
    private String name;

    @Size(min = 10,max = 200,message = Constants.CATEGORY_DESCRIPTION_NOT_VALID)
    private String description;
}
