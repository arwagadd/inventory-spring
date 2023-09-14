package com.example.inventory.controller;

import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.service.ItemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/itemUser")
public class ItemUserController {

    @Autowired
    private ItemUserService itemUserService;

    @PostMapping
    public ItemUserDto giveItemToUser(@RequestBody ItemUserDto itemUserDto)
    {
         return itemUserService.giveItemToUser(itemUserDto);
    }

    @DeleteMapping(path = "/delete/{itemId}/{userId}")
    public void removeItemFromUser(@PathVariable("userId") Integer userId,@PathVariable("itemId") Integer itemId){
             itemUserService.removeItemFromUser(userId,itemId);
    }
}
