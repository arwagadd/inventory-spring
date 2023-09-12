package com.example.inventory.repository;

import com.example.inventory.dto.UserDto;
import com.example.inventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByPhoneNumber(String phoneNumber);
    void deleteById(Integer integer);
    User findById(String id);
}
