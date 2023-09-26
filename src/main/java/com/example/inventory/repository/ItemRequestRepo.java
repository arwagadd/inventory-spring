package com.example.inventory.repository;

import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.enums.ItemRequestType;
import com.example.inventory.enums.RequestStatus;
import com.example.inventory.model.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRequestRepo extends JpaRepository<ItemRequest,Long> {
    List<ItemRequest> findItemRequestsByRequestStatus(RequestStatus requestStatus);
//    ItemRequest findItemRequestTypeById(Long id);
    @Query("select r.itemRequestType from ItemRequest r where r.id =:id ")
    ItemRequestType findItemRequestTypeById(Long id);

    int countByItemRequestType(ItemRequestType type);


}
