package com.example.inventory.controller;


import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.service.ItemRequestService;
import com.example.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/requestItem")
public class ItemRequestController {
    @Autowired
    private ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequestDto requestItem(@RequestBody ItemRequestDto itemRequestDto){
        return itemRequestService.requestItem(itemRequestDto);
    }

    @GetMapping(path = "/pendingRequests")
    public List<ItemRequestDto> viewPendingRequests(){
        return itemRequestService.viewPendingRequests();
    }

    @GetMapping(path = "/acceptedRequests")
    public List<ItemRequestDto> viewAcceptedRequests(){
        return itemRequestService.viewAcceptedRequests();
    }

    @GetMapping(path = "/rejectedRequests")
    public List<ItemRequestDto> viewRejectedRequests(){
        return itemRequestService.viewRejectedRequests();
    }

    @PutMapping(path = "/acceptRequest/{itemRequestId}")
    public void acceptRequest(@PathVariable("itemRequestId") Integer itemRequestId){
         itemRequestService.acceptRequest(itemRequestId);
    }

    @PutMapping(path = "/rejectRequest/{itemRequestId}")
    public void rejectRequest(@PathVariable("itemRequestId") Integer itemRequestId){
        itemRequestService.rejectRequest(itemRequestId);
    }


}
