package com.example.inventory.dto;

import com.example.inventory.model.Item;
import com.example.inventory.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemUserDto {
    private int id;
    private UserDto user;
    private int userId;
    private ItemDto item;
    private int itemId;

  public   static ItemUserDto getInstance(ItemDto itemDto , UserDto userDto){
        ItemUserDto itemUserDto = new ItemUserDto();
        itemUserDto.setItem(itemDto);
        itemUserDto.setUser(userDto);
        return  itemUserDto;
   }
}
