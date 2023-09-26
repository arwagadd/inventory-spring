package com.example.inventory.dto;

import com.example.inventory.model.Item;
import com.example.inventory.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemHistoryDto {
    private Long id;
    private UserDto user;
    private int userId;
    private ItemDto item;
    private Long itemId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
