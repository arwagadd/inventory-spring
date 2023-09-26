package com.example.inventory.service;

import com.example.inventory.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto addItem(ItemDto itemDto);
    ItemDto findItemByName(String name);
    List<ItemDto> viewAllItems();
    void makeItemUnavailable(Long id);
    void makeItemAvailable(Long id);
    void restockItem(Long id, Long quantity);
}
