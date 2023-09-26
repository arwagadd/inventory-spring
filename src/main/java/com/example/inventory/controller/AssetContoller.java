package com.example.inventory.controller;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.enums.ItemRequestType;
import com.example.inventory.model.Asset;
import com.example.inventory.repository.ItemRequestRepo;
import com.example.inventory.service.AssetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/asset")
@AllArgsConstructor
public class AssetContoller {


    private final AssetService assetService;
    private final ItemRequestRepo itemRequestRepo;

    @GetMapping
    public List<AssetDto> viewAllAssets(){
        return assetService.viewAllAssets();
    }
    @PostMapping
    public AssetDto addAsset(@RequestBody AssetDto assetDto){
        return assetService.addAsset(assetDto);
    }

}
