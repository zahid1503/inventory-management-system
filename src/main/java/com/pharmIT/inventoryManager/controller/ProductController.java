package com.pharmIT.inventoryManager.controller;

import com.pharmIT.inventoryManager.request.ProductRequestDto;
import com.pharmIT.inventoryManager.response.ProductResponseDto;
import com.pharmIT.inventoryManager.services.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    // create-product
    @PostMapping()
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto requestDto){
        ProductResponseDto responseDto = productService.addProduct(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    // retrieve product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id){
        ProductResponseDto response = productService.getProductById(id);
        return ResponseEntity.ok().body(response);
    }

    // retrieve all products
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> findAllProducts(){
        List<ProductResponseDto>  response = productService.getProducts();
        return ResponseEntity.ok().body(response);
    }

    // update existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDto requestDto){
        ProductResponseDto response = productService.updateProduct(id ,requestDto);
        return ResponseEntity.ok().body(response);
    }

    // delete existing product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String response = productService.deleteProduct(id);
        return ResponseEntity.ok().body(response);
    }

}
