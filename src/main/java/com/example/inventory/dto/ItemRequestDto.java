package com.example.inventory.dto;


import com.example.inventory.enums.ItemRequestType;
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
    private Long id;
    private UserDto user;
    private Long userId;
    private ItemDto item;
    private Long itemId;
    private Long itemUpgradeId;
    private Long quantity;
    private LocalDateTime requestDateTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RequestStatus requestStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private ItemRequestType itemRequestType;

    public   static ItemRequestDto getInstance(ItemRequestDto requestDto){
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setItem(requestDto.getItem());
        itemRequestDto.setUser(requestDto.getUser());
        itemRequestDto.setQuantity(requestDto.getQuantity());
        itemRequestDto.setRequestDateTime(LocalDateTime.now());
        itemRequestDto.setItemRequestType(requestDto.getItemRequestType());
        itemRequestDto.setItemUpgradeId(requestDto.getItemUpgradeId());
        return  itemRequestDto;
    }
}
