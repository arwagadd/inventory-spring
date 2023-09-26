package com.example.inventory.service;

import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.model.ItemUser;

import java.util.List;

public interface ItemUserService {
    ItemUserDto assignItemToUser(ItemUserDto itemUserDto);
//    List<ItemUserDto> getAllItemsOfUser(Long id);
    void deassignItemFromUser(Long userId, Long itemId);
    List<ItemUserDto> getAllItemsOfUser(Long userId);
}
