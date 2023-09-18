package com.example.inventory.exceptions;

public class ItemQuantityNotAvailable extends RuntimeException{
    public ItemQuantityNotAvailable(String message){
        super(message);
    }
}
