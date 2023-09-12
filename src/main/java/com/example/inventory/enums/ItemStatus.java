package com.example.inventory.enums;


public enum ItemStatus {
    AVAILABLE("AVAILABLE")
    , UNAVAILABLE("UNAVAILABLE");

    private String status;

    ItemStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
