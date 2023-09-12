package com.example.inventory.mapper;

import com.example.inventory.dto.ItemHistoryDto;
import com.example.inventory.model.ItemHistory;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface ItemHistoryMapper {
    ItemHistoryDto entityToDto(ItemHistory itemHistory);

    ItemHistory dtoToEntity(ItemHistoryDto itemHistoryDto);
}
