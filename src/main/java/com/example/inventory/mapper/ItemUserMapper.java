package com.example.inventory.mapper;

import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.model.ItemUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemUserMapper {
    ItemUserDto entityToDto(ItemUser itemUser);

    @Mapping(source = "userId",target = "user.id")
    @Mapping(source = "itemId",target = "item.id")
    ItemUser dtoToEntity(ItemUserDto itemUserDto);
}
