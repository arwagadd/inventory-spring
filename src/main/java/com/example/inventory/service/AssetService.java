package com.example.inventory.service;

import com.example.inventory.dto.AssetDto;

import java.util.List;

public interface AssetService {
    AssetDto addAsset(AssetDto assetDto);
    List<AssetDto> viewAllAssets();
}
