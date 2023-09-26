package com.example.inventory.repository;

import com.example.inventory.dto.UserDto;
import com.example.inventory.enums.JobStatus;
import com.example.inventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByPhoneNumber(String phoneNumber);
    void deleteById(Long id);
    List<User> findByName(String name);
    List<User> findByJobStatus(JobStatus jobStatus);
}
