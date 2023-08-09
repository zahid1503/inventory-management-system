package com.pharmIT.inventoryManager.services;

import com.pharmIT.inventoryManager.request.CategoryRequestDto;
import com.pharmIT.inventoryManager.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    CategoryResponseDto addCategory(CategoryRequestDto requestDto);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getCategories();

    CategoryResponseDto updateCategory(Long id,CategoryRequestDto requestDto);

    String deleteCategory(Long id);
}
