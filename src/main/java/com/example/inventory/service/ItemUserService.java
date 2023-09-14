package com.example.inventory.service;

import com.example.inventory.dto.ItemUserDto;

import java.util.List;

public interface ItemUserService {
    ItemUserDto giveItemToUser(ItemUserDto itemUserDto);
//    List<ItemUserDto> getAllItemsOfUser(Long id);
    void removeItemFromUser(Integer userId,Integer itemId);
}
