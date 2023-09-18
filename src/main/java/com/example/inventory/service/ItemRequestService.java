package com.example.inventory.service;

import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.model.ItemRequest;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto requestItem(ItemRequestDto itemRequestDto);

    List<ItemRequestDto> viewPendingRequests();

    List<ItemRequestDto> viewAcceptedRequests();

    List<ItemRequestDto> viewRejectedRequests();

    void acceptRequest(Integer itemRequestId);

    void rejectRequest(Integer itemRequestId);

}
