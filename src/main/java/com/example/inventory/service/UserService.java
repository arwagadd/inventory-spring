package com.example.inventory.service;

import com.example.inventory.dto.UserDto;
import com.example.inventory.model.User;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    List<UserDto> getAllUsers();
    void deleteById(Integer id);
}
