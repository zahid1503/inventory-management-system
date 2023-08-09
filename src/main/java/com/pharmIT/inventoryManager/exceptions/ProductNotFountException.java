package com.pharmIT.inventoryManager.exceptions;

public class ProductNotFountException extends RuntimeException{

    public ProductNotFountException(String massage){
        super(massage);
    }
}
