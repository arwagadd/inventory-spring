package com.example.inventory.service;

import com.example.inventory.dto.ItemUserDto;

public interface ItemUserService {
    void giveItemToUser(Integer userId, Integer itemId,ItemUserDto itemUserDto);
}
