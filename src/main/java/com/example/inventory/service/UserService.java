package com.example.inventory.service;

import com.example.inventory.dto.UserDto;
import com.example.inventory.enums.JobStatus;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    List<UserDto> getAllUsers();
    void deleteById(Long id);
    List<UserDto> findByName(String name);
    List<UserDto> findAllByJobStatus(JobStatus jobStatus);
}
