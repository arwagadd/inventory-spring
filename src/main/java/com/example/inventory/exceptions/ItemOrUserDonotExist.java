package com.example.inventory.exceptions;

public class ItemOrUserDonotExist extends RuntimeException{
    public ItemOrUserDonotExist(String message){
        super(message);
    }

}
