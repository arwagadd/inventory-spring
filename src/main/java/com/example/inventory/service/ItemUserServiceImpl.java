package com.example.inventory.service;

import com.example.inventory.dto.ItemDto;
import com.example.inventory.dto.ItemHistoryDto;
import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.dto.UserDto;
import com.example.inventory.exceptions.ItemDoesNotExistException;
import com.example.inventory.exceptions.UserDoesNotExistException;
import com.example.inventory.mapper.ItemMapper;
import com.example.inventory.mapper.ItemUserMapper;
import com.example.inventory.mapper.UserMapper;
import com.example.inventory.model.Item;
import com.example.inventory.model.ItemHistory;
import com.example.inventory.model.ItemUser;
import com.example.inventory.model.User;
import com.example.inventory.repository.ItemRepo;
import com.example.inventory.repository.ItemUserHistoryRepo;
import com.example.inventory.repository.ItemUserRepo;
import com.example.inventory.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class ItemUserServiceImpl implements ItemUserService {

    @Autowired
    private ItemUserRepo itemUserRepo;
    private UserRepo userRepo;
    private ItemRepo itemRepo;
    private ItemUserMapper itemUserMapper;
    private UserMapper userMapper;
    private ItemMapper itemMapper;
    private ItemUserHistoryRepo itemUserHistoryRepo;
    private ItemUserHistoryService itemUserHistoryService;


    @Override
    @Transactional
    public ItemUserDto giveItemToUser(ItemUserDto itemUserDto) {
        //if user and item exist, give them to the user.
        UserDto userDto = checkIfUserExists(itemUserDto.getUser().getId());
        ItemDto itemDto = checkIfItemExists(itemUserDto.getItem().getId());
        ItemUserDto itemUser = ItemUserDto.getInstance(itemDto, userDto);
        ItemUser user = itemUserMapper.dtoToEntity(itemUser);
        itemUserRepo.save(user);
        itemUserHistoryService.setHistoryForItem(user);
        return itemUser;
    }

    @Transactional
    @Override
    public void removeItemFromUser(Integer userId, Integer itemId) {
        checkIfUserExists(userId);
        checkIfItemExists(itemId);
        itemUserRepo.deleteByItemIdAndUserId(itemId, userId);
    }

    @Override
    public List<ItemUserDto> getAllItemsOfUser(Integer userId) {
        return itemUserRepo.findAllByUserId(userId).stream().map(itemUserMapper::entityToDto).collect(Collectors.toList());
    }

    public UserDto checkIfUserExists(Integer id) {
        Optional<User> userExists = userRepo.findById(id);
        if (!userExists.isPresent()) {
            throw new UserDoesNotExistException("User does not exist.");
        }
        return userMapper.entityToDto(userExists.get());
    }

    public ItemDto checkIfItemExists(Integer id) {
        Optional<Item> itemExists = itemRepo.findById(id);
        if (!itemExists.isPresent()) {
            //(itemRepo.findByQuantity(id).equals(0)))
            //or if the quanitity of the item is 0
            throw new ItemDoesNotExistException("Item does not exist.");
        }
        return itemMapper.entityToDto(itemExists.get());
    }
}
