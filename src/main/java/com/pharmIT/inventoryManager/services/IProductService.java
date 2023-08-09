package com.pharmIT.inventoryManager.services;

import com.pharmIT.inventoryManager.request.ProductRequestDto;
import com.pharmIT.inventoryManager.response.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {

    ProductResponseDto addProduct(ProductRequestDto requestDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);

    String deleteProduct(Long id);
}
