package com.example.inventory.repository;

import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.enums.RequestStatus;
import com.example.inventory.model.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRequestRepo extends JpaRepository<ItemRequest,Integer> {
    List<ItemRequest> findItemRequestsByRequestStatus(RequestStatus requestStatus);
    ItemRequest findById(int id);

}
