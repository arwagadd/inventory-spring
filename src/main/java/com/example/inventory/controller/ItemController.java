package com.example.inventory.controller;

import com.example.inventory.dto.ItemDto;
import com.example.inventory.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //View all items: Done

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto itemDto) {
        return itemService.addItem(itemDto);
    }

    @GetMapping(path = "/search/{name}")
    public ItemDto findItemByName(@PathVariable("name") String name) {
        return itemService.findItemByName(name);
    }

    @GetMapping(path = "/all")
    public List<ItemDto> viewAllItems(){
        return itemService.viewAllItems();
    }

    @PutMapping(path = "/unavailable/{itemId}")
    public void makeUnavailable(@PathVariable("itemId") Long id){
         itemService.makeItemUnavailable(id);
    }

    @PutMapping(path = "/available/{itemId}")
    public void makeAvailable(@PathVariable("itemId") Long id){
        itemService.makeItemAvailable(id);
    }

    @PutMapping(path = "/restock/{itemId}/{quantity}")
    public void restockItem(@PathVariable("itemId") Long itemId, @PathVariable("quantity") Long quantity){
        itemService.restockItem(itemId,quantity);
    }

}
