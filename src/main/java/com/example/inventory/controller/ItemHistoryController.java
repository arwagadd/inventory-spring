package com.example.inventory.controller;


import com.example.inventory.dto.ItemHistoryDto;
import com.example.inventory.service.ItemUserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/history")
@AllArgsConstructor
public class ItemHistoryController {
    private final ItemUserHistoryService itemUserHistoryService;

    //pagination, 10 at a time: page number, page size
    @GetMapping
    public List<ItemHistoryDto> viewAllHistory() {
        return itemUserHistoryService.viewAllHistory();
    }

    @GetMapping(path = "/item/{itemName}")
    public List<ItemHistoryDto> viewHistoryOfItem(@PathVariable("itemName") String item) {
        return itemUserHistoryService.viewHistoryOfItem(item);
    }

    @GetMapping(path = "/user/{userName}")
    public List<ItemHistoryDto> viewItemHistoryOfUser(@PathVariable("userName") String user) {
        return itemUserHistoryService.viewItemHistoryOfUser(user);
    }

    @GetMapping(path = "/view/{pageNumber}")
    public List<ItemHistoryDto> findItemHistoriesByPage(@PathVariable("pageNumber") int pageNumber) {
        return itemUserHistoryService.findItemHistoriesByPage(pageNumber);
    }

}
