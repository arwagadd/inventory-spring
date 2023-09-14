package com.example.inventory.mapper;

import com.example.inventory.dto.ItemUserDto;
import com.example.inventory.model.ItemUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR , uses = {UserMapper.class})
public interface ItemUserMapper {
    ItemUserDto entityToDto(ItemUser itemUser);

    ItemUser dtoToEntity(ItemUserDto itemUserDto);
}
