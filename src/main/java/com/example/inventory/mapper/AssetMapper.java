package com.example.inventory.mapper;

import com.example.inventory.dto.AssetDto;
import com.example.inventory.model.Asset;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface AssetMapper {
    AssetDto entityToDto(Asset asset);
    Asset dtoToEntity(AssetDto assetDto);
}
