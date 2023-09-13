package com.example.inventory.enums;

public enum JobStatus {

    EMPLOYEE("EMPLOYEE")
    , STAFF("STAFF");

    private String status;

    JobStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
