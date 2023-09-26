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
    private Long id;
    private String name;
    private Long price;
    private String serialNumber;
    private AssetDto asset;
    private Long quantity;
    private Long assetId;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY) --> Get
    private ItemStatus itemStatus;

    private String rating;
    private String description;
}
