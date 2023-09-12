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

    @PostMapping(path = "/api/{userId}/{itemId}")
    public void giveItemToUser(@PathVariable ("userId") Integer userId,
                                      @PathVariable("itemId") Integer itemId,
                               @RequestBody ItemUserDto itemUserDto){
         itemUserService.giveItemToUser(userId,itemId,itemUserDto);
    }
}
