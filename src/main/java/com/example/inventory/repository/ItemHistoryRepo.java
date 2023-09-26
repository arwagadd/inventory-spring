package com.example.inventory.repository;

import com.example.inventory.model.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ItemHistoryRepo extends JpaRepository<ItemHistory,Long> {
    List<ItemHistory> findAllByItemIdAndUserId(Long itemId, Long userId);
    List<ItemHistory> findAllByItemName(String item);
    List<ItemHistory> findAllByUserName(String user);
    List<ItemHistory> findItemHistoriesByPage(Pageable pageable);
}
