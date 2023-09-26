package com.example.inventory.service;

import com.example.inventory.dto.ItemHistoryDto;
import com.example.inventory.model.ItemHistory;
import com.example.inventory.model.ItemUser;

import java.awt.print.Pageable;
import java.util.List;

public interface ItemUserHistoryService {
    void setHistoryForItem(ItemUser itemUser);
    List<ItemHistoryDto> viewAllHistory();
    List<ItemHistoryDto> viewHistoryOfItem(String item);
    List<ItemHistoryDto> viewItemHistoryOfUser(String user);
    List<ItemHistoryDto> findItemHistoriesByPage(int pageNumber);

}
