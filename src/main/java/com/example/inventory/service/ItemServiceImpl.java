package com.example.inventory.service;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.dto.ItemDto;
import com.example.inventory.mapper.ItemMapper;
import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private ItemMapper itemMapper;
    private AssetService assetService;
    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = itemMapper.dtoToEntity(itemDto);
//        AssetDto assetDto = assetService.findById(itemDto.getAsset().getId());
//        item.setAsset();
        itemRepo.save(item);
        return itemMapper.entityToDto(item);
    }
}
