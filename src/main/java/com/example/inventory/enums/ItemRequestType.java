package com.example.inventory.enums;

public enum ItemRequestType {
    New("New"), Replacement("Replacement"), Upgrade("Upgrade");
    private String type;
    ItemRequestType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
}
