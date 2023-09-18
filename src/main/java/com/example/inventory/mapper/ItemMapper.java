package com.example.inventory.mapper;

import com.example.inventory.dto.ItemDto;
import com.example.inventory.model.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface ItemMapper {
   ItemDto entityToDto(Item item);

   @Mapping(target = "itemStatus",ignore = true)
   Item dtoToEntity(ItemDto itemDto);
}
