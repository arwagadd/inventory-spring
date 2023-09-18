package com.example.inventory.service;


import com.example.inventory.dto.ItemHistoryDto;
import com.example.inventory.model.ItemHistory;
import com.example.inventory.model.ItemUser;
import com.example.inventory.repository.ItemUserHistoryRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Service
public class ItemUserHistoryServiceImpl implements ItemUserHistoryService{

    @Autowired
    private ItemUserHistoryRepo itemUserHistoryRepo;

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
        itemUserHistoryRepo.save(itemHistory);
    }
}
