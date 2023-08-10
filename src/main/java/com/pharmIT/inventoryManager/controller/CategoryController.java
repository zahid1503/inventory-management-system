package com.pharmIT.inventoryManager.controller;

import com.pharmIT.inventoryManager.request.CategoryRequestDto;
import com.pharmIT.inventoryManager.response.CategoryResponseDto;
import com.pharmIT.inventoryManager.services.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryResponseDto> addCategory(@Valid @RequestBody CategoryRequestDto requestDto){
        CategoryResponseDto responseDto = categoryService.addCategory(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable Long id){
        CategoryResponseDto response = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> findAllCategories(){
        List<CategoryResponseDto>  response = categoryService.getCategories();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDto requestDto){
        CategoryResponseDto response = categoryService.updateCategory(id ,requestDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        String response = categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(response);
    }
}
