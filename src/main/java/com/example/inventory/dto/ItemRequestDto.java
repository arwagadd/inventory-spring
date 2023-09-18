package com.example.inventory.dto;


import com.example.inventory.enums.RequestStatus;
import com.example.inventory.model.Item;
import com.example.inventory.model.ItemRequest;
import com.example.inventory.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {
    private int id;
    private UserDto user;
    private int userId;
    private ItemDto item;
    private int itemId;
    private int quantity;
    private LocalDateTime requestDateTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RequestStatus requestStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public   static ItemRequestDto getInstance(ItemRequestDto requestDto){
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setItem(requestDto.getItem());
        itemRequestDto.setUser(requestDto.getUser());
        itemRequestDto.setQuantity(requestDto.getQuantity());
        itemRequestDto.setRequestDateTime(LocalDateTime.now());
        return  itemRequestDto;
    }
}
