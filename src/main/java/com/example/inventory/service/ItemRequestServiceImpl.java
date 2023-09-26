package com.example.inventory.service;

import com.example.inventory.dto.ItemDto;
import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.dto.UserDto;
import com.example.inventory.enums.ItemRequestType;
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
import com.example.inventory.model.*;
import com.example.inventory.repository.*;
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
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepo itemRequestRepo;
    private final ItemRequestMapper itemRequestMapper;
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final ItemUserService itemUserService;
    private final ItemUserRepo itemUserRepo;
    private final ItemUserMapper itemUserMapper;
    private final MaintenanceRepo maintenanceRepo;
    private final ItemUserHistoryService itemUserHistoryService;

    @Transactional
    public ItemRequestDto requestItem(ItemRequestDto itemRequestDto) {
        UserDto userDto = checkIfUserExists(itemRequestDto.getUser().getId());
        ItemDto itemDto = checkIfItemExists(itemRequestDto.getItem().getId());
        ItemRequestDto requestDto = ItemRequestDto.getInstance(itemRequestDto);
        ItemRequest itemRequest = itemRequestMapper.dtoToEntity(requestDto);
        itemRequestRepo.save(itemRequest);
        //add to report according to request type
//        if (requestsReportRepo.findAll().isEmpty()) {
//            RequestsReport requestsReport = new RequestsReport();
//            requestsReportRepo.save(requestsReport);
//            if (itemRequestDto.getItemRequestType().equals(ItemRequestType.Replacement)) {
//                requestsReportRepo.findById(1L).get().setMaintenanceRequests(1L);
//            } else if (itemRequestDto.getItemRequestType().equals(ItemRequestType.New)) {
//                requestsReportRepo.findById(1L).get().setNewRequests(1L);
//            } else {
//                requestsReportRepo.findById(1L).get().setMaintenanceRequests(1L);
//            }
//            requestsReportRepo.save(requestsReport);
//        } else {
//            if (itemRequestDto.getItemRequestType().equals(ItemRequestType.Upgrade)) {
//                requestsReportRepo.findById(1L).get().setMaintenanceRequests(requestsReportRepo.findById(1L).get().getMaintenanceRequests() + 1);
//            }
//            if (itemRequestDto.getItemRequestType().equals(ItemRequestType.Replacement)) {
//                requestsReportRepo.findById(1L).get().setReplacementRequests(requestsReportRepo.findById(1L).get().getReplacementRequests() + 1);
//            } else {
//                if (itemRequestDto.getItemRequestType().equals(ItemRequestType.New)) {
//                    requestsReportRepo.findById(1L).get().setNewRequests(requestsReportRepo.findById(1L).get().getNewRequests() + 1);
//                }
//            }
//            requestsReportRepo.save(requestsReportRepo.findById(1L).get());
//        }
        return requestDto;
    }

    @Override
    public List<ItemRequestDto> viewRequests(RequestStatus requestStatus) {
        List<ItemRequest> itemRequest = itemRequestRepo.findItemRequestsByRequestStatus(requestStatus);
        return itemRequest.stream().map(itemRequestMapper::entityToDto).collect(Collectors.toList());
    }
//    public List<ItemRequestDto> viewAcceptedRequests(){
//        List<ItemRequest> itemRequest =  itemRequestRepo.findItemRequestsByRequestStatus(RequestStatus.Accepted);
//        return itemRequest.stream().map(itemRequestMapper::entityToDto).collect(Collectors.toList());
//    }
//    public List<ItemRequestDto> viewRejectedRequests(){
//        List<ItemRequest> itemRequest =  itemRequestRepo.findItemRequestsByRequestStatus(RequestStatus.Rejected);
//        return itemRequest.stream().map(itemRequestMapper::entityToDto).collect(Collectors.toList());
//    }

    @Override
    public void rejectRequest(Long itemRequestId) {
        Optional<ItemRequest> itemRequestExists = itemRequestRepo.findById(itemRequestId);
        if (!itemRequestExists.isPresent()) {
            throw new RequestNotFoundException("Request not found");
        } else {
            Long requestId = itemRequestExists.get().getItemId();
            itemRequestRepo.findById(requestId);
            Optional<Item> item = itemRepo.findById(requestId);
            if (itemRequestExists.get().getQuantity() >= item.get().getQuantity() || item.get().getItemStatus().equals(ItemStatus.UNAVAILABLE) || item.get().getQuantity() == 0) {
                itemRequestExists.get().setRequestStatus(RequestStatus.Rejected);
            }
            itemRequestRepo.save(itemRequestExists.get());
        }
    }

    @Override
    public int countByItemRequestType(ItemRequestType type) {
        System.out.println(itemRequestRepo.countByItemRequestType(ItemRequestType.New));
        return 0;
    }

    @Override
    @Transactional
    public void acceptRequest(Long itemRequestId) {
        Optional<ItemRequest> itemRequestExists = itemRequestRepo.findById(itemRequestId);
        if (!itemRequestExists.isPresent())
            throw new RequestNotFoundException("Request not found");

        Long requestId = itemRequestExists.get().getItemId();
        itemRequestRepo.findById(requestId);
        Optional<Item> item = itemRepo.findById(requestId);
        itemRequestExists.get().setRequestStatus(RequestStatus.Accepted);
        itemRequestRepo.save(itemRequestExists.get());

        Optional<Item> itemExists = itemRepo.findById(itemRequestExists.get().getItemId());
        itemExists.get().setQuantity(itemExists.get().getQuantity() - itemRequestExists.get().getQuantity());
        if (itemExists.get().getQuantity() < 0) {
            throw new ItemQuantityNotAvailable("Item Quantity not Available");
        }
        if (itemExists.get().getItemStatus() == ItemStatus.UNAVAILABLE) {
            throw new ItemDoesNotExistException("Item is not available");
        }
        if (itemExists.get().getQuantity() == 0) {
            itemExists.get().setItemStatus(ItemStatus.UNAVAILABLE);
        }
        itemRepo.save(itemExists.get());

        ItemUser itemUser = new ItemUser();
        itemUser.setItem(itemRequestExists.get().getItem());
        itemUser.setUser(itemRequestExists.get().getUser());
        itemUserRepo.save(itemUser);
        itemUserHistoryService.setHistoryForItem(itemUser);
//        itemUserService.assignItemToUser(itemUserMapper.entityToDto(itemUser));

        if (itemRequestRepo.findItemRequestTypeById(itemRequestId) == ItemRequestType.Upgrade) {
            Maintenance maintenance = new Maintenance();
            maintenance.setItem(itemRequestExists.get().getItem());
            maintenance.setUpgradeItemId(itemRequestExists.get().getItemUpgradeId());
            maintenanceRepo.save(maintenance);
        }

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

    public UserDto checkIfUserExists(Long id) {
        Optional<User> userExists = userRepo.findById(id);
        if (!userExists.isPresent()) {
            throw new UserDoesNotExistException("User does not exist.");
        }
        return userMapper.entityToDto(userExists.get());
    }

    public ItemDto checkIfItemExists(Long id) {
        Optional<Item> itemExists = itemRepo.findById(id);
        if (!itemExists.isPresent()) {
            //(itemRepo.findByQuantity(id).equals(0)))
            //or if the quanitity of the item is 0
            throw new ItemDoesNotExistException("Item does not exist.");
        }
        return itemMapper.entityToDto(itemExists.get());
    }
}
