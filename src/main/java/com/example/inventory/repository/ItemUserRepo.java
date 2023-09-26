package com.example.inventory.repository;

import com.example.inventory.model.ItemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemUserRepo extends JpaRepository<ItemUser,Long> {
    void deleteByItemIdAndUserId(Long itemId, Long userId);
    List<ItemUser> findAllByUserId(Long userId);
}
