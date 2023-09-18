package com.example.inventory.service;


import com.example.inventory.dto.ItemDto;
import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.dto.UserDto;
import com.example.inventory.enums.ItemStatus;
import com.example.inventory.enums.RequestStatus;
import com.example.inventory.exceptions.ItemDoesNotExistException;
import com.example.inventory.exceptions.ItemQuantityNotAvailable;
import com.example.inventory.exceptions.RequestNotFoundException;
import com.example.inventory.exceptions.UserDoesNotExistException;
import com.example.inventory.mapper.ItemMapper;
import com.example.inventory.mapper.ItemRequestMapper;
import com.example.inventory.mapper.ItemUserMapper;
import com.example.inventory.mapper.UserMapper;
import com.example.inventory.model.Item;
import com.example.inventory.model.ItemRequest;
import com.example.inventory.model.ItemUser;
import com.example.inventory.model.User;
import com.example.inventory.repository.ItemRepo;
import com.example.inventory.repository.ItemRequestRepo;
import com.example.inventory.repository.ItemUserRepo;
import com.example.inventory.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class ItemRequestServiceImpl implements ItemRequestService{

    @Autowired
    private ItemRequestRepo itemRequestRepo;
    private ItemRequestMapper itemRequestMapper;
    private ItemRepo itemRepo;
    private UserRepo userRepo;
    private UserMapper userMapper;
    private ItemMapper itemMapper;
    private ItemUserService itemUserService;
    private ItemUserRepo itemUserRepo;
    private ItemUserMapper itemUserMapper;

    @Transactional
    public ItemRequestDto requestItem(ItemRequestDto itemRequestDto) {
        UserDto userDto = checkIfUserExists(itemRequestDto.getUser().getId());
        ItemDto itemDto = checkIfItemExists(itemRequestDto.getItem().getId());
        ItemRequestDto requestDto = ItemRequestDto.getInstance(itemRequestDto);
        ItemRequest itemRequest = itemRequestMapper.dtoToEntity(requestDto);
        itemRequestRepo.save(itemRequest);
        return requestDto;
    }

    @Override
    public List<ItemRequestDto> viewPendingRequests() {
        List<ItemRequest> itemRequest = itemRequestRepo.findItemRequestsByRequestStatus(RequestStatus.Pending);
        return itemRequest.stream().map(itemRequestMapper::entityToDto).collect(Collectors.toList());
    }
    public List<ItemRequestDto> viewAcceptedRequests(){
        List<ItemRequest> itemRequest =  itemRequestRepo.findItemRequestsByRequestStatus(RequestStatus.Accepted);
        return itemRequest.stream().map(itemRequestMapper::entityToDto).collect(Collectors.toList());
    }
    public List<ItemRequestDto> viewRejectedRequests(){
        List<ItemRequest> itemRequest =  itemRequestRepo.findItemRequestsByRequestStatus(RequestStatus.Rejected);
        return itemRequest.stream().map(itemRequestMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void rejectRequest(Integer itemRequestId) {
        Optional<ItemRequest> itemRequestExists = itemRequestRepo.findById(itemRequestId);
        if(!itemRequestExists.isPresent()){
            throw new RequestNotFoundException("Request not found");
        }
        else {
            Integer requestId = itemRequestExists.get().getItemId();
            itemRequestRepo.findById(requestId);
            Optional<Item> item = itemRepo.findById(requestId);
            if(itemRequestExists.get().getQuantity() >= item.get().getQuantity() || item.get().getItemStatus().equals(ItemStatus.UNAVAILABLE) || item.get().getQuantity() == 0){
                itemRequestExists.get().setRequestStatus(RequestStatus.Rejected);
            }
            itemRequestRepo.save(itemRequestExists.get());
        }
    }
    @Override
    public void acceptRequest(Integer itemRequestId) {
        Optional<ItemRequest> itemRequestExists = itemRequestRepo.findById(itemRequestId);
        if(!itemRequestExists.isPresent()){
            throw new RequestNotFoundException("Request not found");
        }
        else {
            Integer requestId = itemRequestExists.get().getItemId();
            itemRequestRepo.findById(requestId);
            Optional<Item> item = itemRepo.findById(requestId);
            itemRequestExists.get().setRequestStatus(RequestStatus.Accepted);
        }
        itemRequestRepo.save(itemRequestExists.get());
        ItemUser itemUser = new ItemUser();
        itemUser.setItem(itemRequestExists.get().getItem());
        itemUser.setUser(itemRequestExists.get().getUser());
        itemUserRepo.save(itemUser);
        itemUserService.giveItemToUser(itemUserMapper.entityToDto(itemUser));
    }

//    @Override
//    @Transactional
//    public void acceptOrRejectRequest(Integer requestId) {
//        Optional<ItemRequest> itemRequestExists = itemRequestRepo.findById(requestId);
//        if(!itemRequestExists.isPresent()){
//            throw new RequestNotFoundException("Request not found");
//        }
//        else {
//            Integer itemRequestId = itemRequestExists.get().getItemId();
//            itemRequestRepo.findById(itemRequestId);
//            Optional<Item> item = itemRepo.findById(itemRequestId);
//            if(itemRequestExists.get().getQuantity() >= item.get().getQuantity() || item.get().getItemStatus().equals(ItemStatus.UNAVAILABLE) || item.get().getQuantity() == 0){
//                itemRequestExists.get().setRequestStatus(RequestStatus.Rejected);
//            }
//            else {
//                itemRequestExists.get().setRequestStatus(RequestStatus.Accepted);
//            }
//            itemRequestRepo.save(itemRequestExists.get());
//        }
//
//    }

    public UserDto checkIfUserExists(Integer id) {
        Optional<User> userExists = userRepo.findById(id);
        if (!userExists.isPresent()) {
            throw new UserDoesNotExistException("User does not exist.");
        }
        return userMapper.entityToDto(userExists.get());
    }

    public ItemDto checkIfItemExists(Integer id) {
        Optional<Item> itemExists = itemRepo.findById(id);
        if (!itemExists.isPresent()) {
            //(itemRepo.findByQuantity(id).equals(0)))
            //or if the quanitity of the item is 0
            throw new ItemDoesNotExistException("Item does not exist.");
        }
        return itemMapper.entityToDto(itemExists.get());
    }
}
