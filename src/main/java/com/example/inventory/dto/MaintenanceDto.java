package com.example.inventory.dto;

import com.example.inventory.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDto {
    private int id;
    private Item item;
    private int itemId;
    private String action;
    private int price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
