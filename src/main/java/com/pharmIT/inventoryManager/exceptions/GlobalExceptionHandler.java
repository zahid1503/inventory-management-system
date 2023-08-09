package com.pharmIT.inventoryManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFountException.class)
    @ResponseBody
    public ResponseEntity<Map<String,String>> handleProductNotFoundException(ProductNotFountException ex) {
        Map<String,String> responseMap = new HashMap<>();
        String fieldName = "product";
        String message = ex.getMessage();
        responseMap.put(fieldName,message);

        return new ResponseEntity<Map<String,String>>(responseMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFountException.class)
    @ResponseBody
    public ResponseEntity<Map<String,String>> handleCategoryNotFountException(CategoryNotFountException ex) {
        Map<String,String> responseMap = new HashMap<>();
        String fieldName = "category";
        String message = ex.getMessage();
        responseMap.put(fieldName,message);

        return new ResponseEntity<Map<String,String>>(responseMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String,String>  responseMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            responseMap.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(responseMap, HttpStatus.BAD_REQUEST);
    }
}

