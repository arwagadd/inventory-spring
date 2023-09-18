package com.example.inventory.service;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.dto.ItemDto;
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

import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private ItemMapper itemMapper;
    private AssetService assetService;
    private AssetRepo assetRepo;

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
}
