package com.example.inventory.enums;


public enum RequestStatus {
    Accepted("Accepted"),
    Rejected("Rejected"),
    Pending("Pending");
    private String status;
    RequestStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
}
