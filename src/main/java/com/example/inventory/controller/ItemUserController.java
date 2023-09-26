package com.example.inventory.controller;

import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.model.ItemUser;
import com.example.inventory.service.ItemUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/itemUser")
@AllArgsConstructor
public class ItemUserController {

    private final ItemUserService itemUserService;

    //assign and deassign: Done
    @PostMapping
    public ItemUserDto assignItemToUser(@RequestBody ItemUserDto itemUserDto)
    {
         return itemUserService.assignItemToUser(itemUserDto);
    }

    //end date in history table: Done
    @PutMapping(path = "/deassign/{itemId}/{userId}")
    public void deassignItemFromUser(@PathVariable("userId") Long userId,@PathVariable("itemId") Long itemId){
             itemUserService.deassignItemFromUser(userId,itemId);
    }

    @GetMapping(path = "/getItems/{userId}")
    public List<ItemUserDto> getAllItemsOfUser(@PathVariable("userId") Long userId){
        return  itemUserService.getAllItemsOfUser(userId);
    }
}
