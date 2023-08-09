package com.pharmIT.inventoryManager.services;

import com.pharmIT.inventoryManager.entities.Category;
import com.pharmIT.inventoryManager.exceptions.CategoryNotFountException;
import com.pharmIT.inventoryManager.repository.CategoryRepository;
import com.pharmIT.inventoryManager.request.CategoryRequestDto;
import com.pharmIT.inventoryManager.response.CategoryResponseDto;
import com.pharmIT.inventoryManager.utility.Constants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto requestDto) {
        log.debug("creating category starts");

        Category newCategory = modelMapper.map(requestDto, Category.class);
        Category savedEntity = categoryRepository.save(newCategory);
        CategoryResponseDto response = modelMapper.map(savedEntity, CategoryResponseDto.class);

        log.debug("creating product ends");
        return response;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        log.debug("fetching category starts");
        try{
            Category category = categoryRepository.findById(id).
                    orElseThrow(() -> new CategoryNotFountException(Constants.CATEGORY_NOT_FOUND));

            CategoryResponseDto responseDto = modelMapper.map(category,CategoryResponseDto.class);
            return responseDto;
        }catch (Exception ex){
            if(Constants.CATEGORY_NOT_FOUND.equals(ex.getMessage())){
                throw new CategoryNotFountException(Constants.CATEGORY_NOT_FOUND);
            }
            throw new CategoryNotFountException(Constants.FETCHING_CATEGORY_FAILED);
        }
    }

    @Override
    public List<CategoryResponseDto> getCategories() {

        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponseDto> responseDtoList = null;

        if (!categories.isEmpty()) {

            responseDtoList = categories.stream()
                    .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                    .peek(categoryResponse -> log.info("mapped category entity to response {}", categoryResponse))
                    .collect(Collectors.toList());
        }
        return responseDtoList;
    }

    @Override
    public CategoryResponseDto updateCategory(Long id ,CategoryRequestDto requestDto) {
        log.debug("updating category starts");
        try{
            Category category = categoryRepository.findById(id).
                    orElseThrow(() -> new CategoryNotFountException(Constants.CATEGORY_NOT_FOUND));

            Category newCategory = modelMapper.map(requestDto, Category.class);
            newCategory.setId(id);
            Category savedEntity = categoryRepository.save(newCategory);
            CategoryResponseDto response = modelMapper.map(savedEntity, CategoryResponseDto.class);

            log.debug("updating product ends");
            return response;
        }catch (Exception ex){
            if (Constants.CATEGORY_NOT_FOUND.equals(ex.getMessage())) {
                throw new CategoryNotFountException(Constants.CATEGORY_NOT_FOUND);
            }
            throw new CategoryNotFountException(Constants.UPDATING_CATEGORY_FAILED);
        }
    }

    @Override
    public String deleteCategory(Long id) {
        log.debug("deleting category starts");
        try{

            Category category = categoryRepository.findById(id).
                    orElseThrow(() -> new CategoryNotFountException(Constants.CATEGORY_NOT_FOUND));

            categoryRepository.delete(category);

            return "given category number: " + id + " is deleted successfully";
        }catch (Exception ex){
            if (Constants.CATEGORY_NOT_FOUND.equals(ex.getMessage())) {
                throw new CategoryNotFountException(Constants.CATEGORY_NOT_FOUND);
            }
            throw new CategoryNotFountException(Constants.DELETING_CATEGORY_FAILED);
        }
    }


}
