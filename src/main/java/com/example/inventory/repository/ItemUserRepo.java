package com.example.inventory.repository;

import com.example.inventory.model.ItemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemUserRepo extends JpaRepository<ItemUser,Long> {
    void deleteByItemIdAndUserId(Integer itemId, Integer userId);
}
