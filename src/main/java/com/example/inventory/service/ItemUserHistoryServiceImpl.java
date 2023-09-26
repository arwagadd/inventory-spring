package com.example.inventory.service;


import com.example.inventory.dto.ItemHistoryDto;
import com.example.inventory.mapper.ItemHistoryMapper;
import com.example.inventory.model.ItemHistory;
import com.example.inventory.model.ItemUser;
import com.example.inventory.repository.ItemHistoryRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class ItemUserHistoryServiceImpl implements ItemUserHistoryService{

    private final ItemHistoryMapper itemHistoryMapper;
    private final ItemHistoryRepo itemHistoryRepo;

    @Transactional
    public void setHistoryForItem(ItemUser itemUser){
//        ItemHistoryDto itemHistoryDto = new ItemHistoryDto();
//        itemHistoryDto.setItemId(itemUser.getItemId());
//        itemHistoryDto.setUserId(itemUser.getUserId());
//        itemHistoryDto.setStartTime(LocalDateTime.now());
        ItemHistory itemHistory = new ItemHistory();
        itemHistory.setItemId(itemUser.getItemId());
        itemHistory.setUserId(itemUser.getUserId());
        itemHistory.setStartTime(LocalDateTime.now());
        itemHistory.setItem(itemUser.getItem());
        itemHistory.setUser(itemUser.getUser());
        itemHistoryRepo.save(itemHistory);
    }
    @Override
    public List<ItemHistoryDto> viewAllHistory() {
        return itemHistoryRepo.findAll().stream().map(itemHistoryMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemHistoryDto> viewHistoryOfItem(String item) {
        return itemHistoryRepo.findAllByItemName(item).stream().map(itemHistoryMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemHistoryDto> viewItemHistoryOfUser(String user) {
        return itemHistoryRepo.findAllByUserName(user).stream().map(itemHistoryMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemHistoryDto> findItemHistoriesByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return itemHistoryRepo.findItemHistoriesByPage(pageable).stream().map(itemHistoryMapper::entityToDto).collect(Collectors.toList());
    }
}
