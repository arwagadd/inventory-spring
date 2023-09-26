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
    private Long id;
    private Item item;
    private Long itemId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long upgradeItemId;
}
