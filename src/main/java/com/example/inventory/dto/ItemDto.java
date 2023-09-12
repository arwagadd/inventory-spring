package com.example.inventory.dto;

import com.example.inventory.enums.ItemStatus;
import com.example.inventory.model.Asset;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private int id;
    private String name;
    private int price;
    private String serialNumber;
    private AssetDto asset;
    private int quantity=0;
    private int assetId;
    private ItemStatus itemStatus = ItemStatus.UNAVAILABLE;
    private String rating;
    private String description;
}
