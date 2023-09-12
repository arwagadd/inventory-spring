package com.example.inventory.dto;

import com.example.inventory.model.Item;
import com.example.inventory.model.User;

import java.time.LocalDateTime;

public class ItemHistoryDto {
    private int id;
    private UserDto user;
    private int userId;
    private ItemDto item;
    private int itemId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
