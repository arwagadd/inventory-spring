package com.example.inventory.service;

import com.example.inventory.dto.UserDto;
import com.example.inventory.exceptions.UserAlreadyExistsException;
import com.example.inventory.mapper.UserMapper;
import com.example.inventory.model.User;
import com.example.inventory.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public UserDto addUser(UserDto userDto) {
        findByPhoneNumber(userDto.getPhoneNumber());
        User user = userMapper.dtoToEntity(userDto);
        userRepo.save(user);
        return userMapper.entityToDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }

    public void findByPhoneNumber(String phoneNumber) {
        User user = userRepo.findByPhoneNumber(phoneNumber);
        if (user != null) {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }
}
