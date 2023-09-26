package com.example.inventory.service;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.mapper.AssetMapper;
import com.example.inventory.model.Asset;
import com.example.inventory.repository.AssetRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class AssetServiceImpl implements AssetService{
    private final AssetRepo assetRepo;
    private final AssetMapper assetMapper;

    @Transactional
    @Override
    public AssetDto addAsset(AssetDto assetDto) {
        Asset asset = assetMapper.dtoToEntity(assetDto);
        assetRepo.save(asset);
        return assetMapper.entityToDto(asset);
    }

    @Override
    public List<AssetDto> viewAllAssets() {
        return assetRepo.findAll().stream().map(assetMapper::entityToDto).collect(Collectors.toList());
    }
}
