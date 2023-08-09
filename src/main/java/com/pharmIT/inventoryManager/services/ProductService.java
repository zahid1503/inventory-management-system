package com.pharmIT.inventoryManager.services;


import com.pharmIT.inventoryManager.entities.Product;
import com.pharmIT.inventoryManager.exceptions.ProductNotFountException;
import com.pharmIT.inventoryManager.repository.ProductRepository;
import com.pharmIT.inventoryManager.request.ProductRequestDto;
import com.pharmIT.inventoryManager.response.ProductResponseDto;
import com.pharmIT.inventoryManager.utility.Constants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        log.debug("adding product starts");

        Product product = modelMapper.map(requestDto, Product.class);
        Product savedEntity = productRepository.save(product);
        ProductResponseDto response = modelMapper.map(savedEntity, ProductResponseDto.class);

        log.debug("adding product ends");
        return response;
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        log.debug("fetching product starts");
        try{
            Product product = productRepository.findById(id).
                    orElseThrow(() -> new ProductNotFountException(Constants.PRODUCT_NOT_FOUND));

            ProductResponseDto responseDto = modelMapper.map(product,ProductResponseDto.class);
            return responseDto;
        }catch (Exception ex){
            if(Constants.PRODUCT_NOT_FOUND.equals(ex.getMessage())) {
                throw new ProductNotFountException(Constants.PRODUCT_NOT_FOUND);
            }
            throw new ProductNotFountException(Constants.FETCHING_PRODUCT_FAILED);
        }
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        log.debug("fetching all products starts");

        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> responseDtoList = null;

        if (!products.isEmpty()) {
            responseDtoList = products.stream()
                    .map(product -> modelMapper.map(product, ProductResponseDto.class))
                    .peek(productResponse -> log.info("mapped product entity to response {}", productResponse))
                    .collect(Collectors.toList());
        }
        log.debug("fetching all product ends");
        return responseDtoList;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        log.debug("updating product starts");

        try{
            Product product = productRepository.findById(id).
                    orElseThrow(() -> new ProductNotFountException(Constants.PRODUCT_NOT_FOUND));

            Product updatedProduct = modelMapper.map(requestDto, Product.class);
            updatedProduct.setId(id);
            Product savedEntity = productRepository.save(updatedProduct);
            ProductResponseDto response = modelMapper.map(savedEntity,ProductResponseDto.class);

            log.debug("updating product ends");
            return response;
        }catch (Exception ex) {
            if (Constants.PRODUCT_NOT_FOUND.equals(ex.getMessage())) {
                throw new ProductNotFountException(Constants.PRODUCT_NOT_FOUND);
            }
            throw new ProductNotFountException(Constants.UPDATING_PRODUCT_FAILED);
        }
    }

    @Override
    public String deleteProduct(Long id) {
        log.debug("deleting product starts");
        try{
            Product product = productRepository.findById(id).
                    orElseThrow(() -> new ProductNotFountException(Constants.PRODUCT_NOT_FOUND));

            productRepository.delete(product);

            return "given product number: " + id + " is deleted successfully";
        }catch (Exception ex){
            if (Constants.PRODUCT_NOT_FOUND.equals(ex.getMessage())) {
                throw new ProductNotFountException(Constants.PRODUCT_NOT_FOUND);
            }
            throw new ProductNotFountException(Constants.DELETING_PRODUCT_FAILED);
        }
    }


}
