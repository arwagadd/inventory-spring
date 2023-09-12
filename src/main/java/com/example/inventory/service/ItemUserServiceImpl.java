package com.example.inventory.service;

import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.exceptions.ItemOrUserDonotExist;
import com.example.inventory.mapper.ItemUserMapper;
import com.example.inventory.model.Item;
import com.example.inventory.model.ItemUser;
import com.example.inventory.model.User;
import com.example.inventory.repository.ItemRepo;
import com.example.inventory.repository.ItemUserRepo;
import com.example.inventory.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class ItemUserServiceImpl implements ItemUserService {

    @Autowired
    private ItemUserRepo itemUserRepo;
    private UserRepo userRepo;
    private ItemRepo itemRepo;
    private ItemUserMapper itemUserMapper;

    @Override
    public void giveItemToUser(Integer userId, Integer itemId,ItemUserDto itemUserDto) {
        //if user and item exist, give them to the user.
        Optional<User> user = userRepo.findById(userId);
        Optional<Item> item = itemRepo.findById(itemId);
        if(user.isPresent() && item.isPresent()){
            itemUserDto.setUserId(userId);
            itemUserDto.setItemId(itemId);
        }
    }
}
