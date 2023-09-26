package com.example.inventory.mapper;

import com.example.inventory.dto.ItemRequestDto;
import com.example.inventory.model.ItemRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemRequestMapper {
    ItemRequestDto entityToDto(ItemRequest itemRequest);

    @Mapping(target = "requestStatus", ignore = true)
    @Mapping(target = "itemRequestType", ignore = false)
    ItemRequest dtoToEntity(ItemRequestDto itemRequestDto);
}
