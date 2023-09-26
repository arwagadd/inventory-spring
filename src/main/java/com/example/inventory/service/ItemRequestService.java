package com.example.inventory.service;

import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.enums.ItemRequestType;
import com.example.inventory.enums.RequestStatus;
import com.example.inventory.model.ItemRequest;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto requestItem(ItemRequestDto itemRequestDto);

    List<ItemRequestDto> viewRequests(RequestStatus requestStatus);

    void acceptRequest(Long itemRequestId);

    void rejectRequest(Long itemRequestId);

    int countByItemRequestType(ItemRequestType type);


}
