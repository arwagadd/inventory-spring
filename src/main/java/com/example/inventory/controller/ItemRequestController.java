package com.example.inventory.controller;


import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.enums.ItemRequestType;
import com.example.inventory.enums.RequestStatus;
import com.example.inventory.repository.ItemRequestRepo;
import com.example.inventory.service.ItemRequestService;
import com.example.inventory.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/requestItem")
@AllArgsConstructor
public class ItemRequestController {

    private final ItemRequestService itemRequestService;
    private final ItemRequestRepo itemRequestRepo;

    @PostMapping
    public ItemRequestDto requestItem(@RequestBody ItemRequestDto itemRequestDto){
        return itemRequestService.requestItem(itemRequestDto);
    }

    //Adjust : Done
    @GetMapping(path = "/{requestStatus}")
    public List<ItemRequestDto> viewRequests(@PathVariable("requestStatus") RequestStatus requestStatus){
        return itemRequestService.viewRequests(requestStatus);
    }

//    @GetMapping(path = "/acceptedRequests")
//    public List<ItemRequestDto> viewAcceptedRequests(){
//        return itemRequestService.viewAcceptedRequests();
//    }
//
//    @GetMapping(path = "/rejectedRequests")
//    public List<ItemRequestDto> viewRejectedRequests(){
//        return itemRequestService.viewRejectedRequests();
//    }

    @PutMapping(path = "/acceptRequest/{itemRequestId}")
    public void acceptRequest(@PathVariable("itemRequestId") Long itemRequestId){
         itemRequestService.acceptRequest(itemRequestId);
    }

    @PutMapping(path = "/rejectRequest/{itemRequestId}")
    public void rejectRequest(@PathVariable("itemRequestId") Long itemRequestId){
        itemRequestService.rejectRequest(itemRequestId);
    }

    @GetMapping(path = "/count/{requestType}")
    public int countRequests(@PathVariable("requestType") ItemRequestType itemRequestType){
        return itemRequestRepo.countByItemRequestType(itemRequestType);
    }

}
