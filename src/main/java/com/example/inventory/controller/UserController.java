package com.example.inventory.controller;

import com.example.inventory.dto.UserDto;
import com.example.inventory.enums.JobStatus;
import com.example.inventory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/search/{name}")
    public List<UserDto> findUsersByName(@PathVariable("name") String name){
        return userService.findByName(name);
    }
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public void deleteById(@PathVariable("userId") Long userId) {
        userService.deleteById(userId);
    }

    @GetMapping(path = "/{jobStatus}")
    public List<UserDto> findAllEmployees(@PathVariable("jobStatus") JobStatus jobStatus){
        return userService.findAllByJobStatus(jobStatus);
    }
}
