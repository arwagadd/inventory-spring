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
    private Long id;
    private UserDto user;
    private Long userId;
    private ItemDto item;
    private Long itemId;

  public   static ItemUserDto getInstance(ItemDto itemDto , UserDto userDto){
        ItemUserDto itemUserDto = new ItemUserDto();
        itemUserDto.setItem(itemDto);
        itemUserDto.setUser(userDto);
        return  itemUserDto;
   }
}
