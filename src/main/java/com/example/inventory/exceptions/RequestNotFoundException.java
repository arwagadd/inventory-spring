package com.example.inventory.exceptions;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(String message){
        super(message);
    }
}
