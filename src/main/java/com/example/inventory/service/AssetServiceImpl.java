package com.example.inventory.service;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.mapper.AssetMapper;
import com.example.inventory.model.Asset;
import com.example.inventory.repository.AssetRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class AssetServiceImpl implements AssetService{
    @Autowired
    private AssetRepo assetRepo;
    private AssetMapper assetMapper;

    @Override
    public AssetDto addAsset(AssetDto assetDto) {
        Asset asset = assetMapper.dtoToEntity(assetDto);
        assetRepo.save(asset);
        return assetMapper.entityToDto(asset);
    }
}
