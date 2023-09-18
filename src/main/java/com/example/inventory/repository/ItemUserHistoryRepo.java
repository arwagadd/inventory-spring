package com.example.inventory.repository;

import com.example.inventory.model.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemUserHistoryRepo extends JpaRepository<ItemHistory, Integer> {

}
