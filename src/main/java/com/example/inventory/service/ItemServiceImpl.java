package com.example.inventory.service;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.dto.ItemDto;
import com.example.inventory.enums.ItemStatus;
import com.example.inventory.exceptions.ItemDoesNotExistException;
import com.example.inventory.mapper.ItemMapper;
import com.example.inventory.model.Asset;
import com.example.inventory.model.Item;
import com.example.inventory.repository.AssetRepo;
import com.example.inventory.repository.ItemRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;
    private final AssetService assetService;
    private final AssetRepo assetRepo;

    @Transactional
    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = itemMapper.dtoToEntity(itemDto);
//        Optional<Asset> asset = assetRepo.findById(itemDto.getAsset().getId());
//        itemDto.setAssetId(asset.get().getId());
        itemRepo.save(item);
        //ItemDto -> Asset { id , name }, assetID
        return itemMapper.entityToDto(item);
    }

    @Override
    public ItemDto findItemByName(String name) {
        Item item = itemRepo.findByName(name);
        itemRepo.save(item);
        return itemMapper.entityToDto(item);
    }

    @Override
    public List<ItemDto> viewAllItems() {
        return itemRepo.findAll().stream().map(itemMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void makeItemUnavailable(Long id) {
        checkIfItemExists(id);
        Optional<Item> item = itemRepo.findById(id);
        item.get().setItemStatus(ItemStatus.UNAVAILABLE);
        itemRepo.save(item.get());
    }

    @Override
    public void makeItemAvailable(Long id) {
        checkIfItemExists(id);
        Optional<Item> item = itemRepo.findById(id);
        item.get().setItemStatus(ItemStatus.AVAILABLE);
        itemRepo.save(item.get());
    }

    @Override
    @Transactional
    public void restockItem(Long id, Long quantity) {
        Optional<Item> itemExists = itemRepo.findById(id);
        itemExists.get().setQuantity(quantity);
        itemRepo.save(itemExists.get());

    }

    public ItemDto checkIfItemExists(Long id) {
        Optional<Item> itemExists = itemRepo.findById(id);
        if (!itemExists.isPresent()) {
            //(itemRepo.findByQuantity(id).equals(0)))
            //or if the quanitity of the item is 0
            throw new ItemDoesNotExistException("Item does not exist.");
        }
        return itemMapper.entityToDto(itemExists.get());
    }
}
