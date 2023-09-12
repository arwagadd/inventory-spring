package com.example.inventory.controller;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.model.Asset;
import com.example.inventory.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/asset")
public class AssetContoller {
    @Autowired
    private AssetService assetService;

    @PostMapping
    public AssetDto addAsset(@RequestBody AssetDto assetDto){
        return assetService.addAsset(assetDto);
    }
}
