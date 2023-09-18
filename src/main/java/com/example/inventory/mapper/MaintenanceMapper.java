package com.example.inventory.mapper;

import com.example.inventory.dto.MaintenanceDto;
import com.example.inventory.model.Maintenance;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MaintenanceMapper {
    MaintenanceDto entityToDto(Maintenance maintenance);
    Maintenance dtoToEntity(MaintenanceDto maintenanceDto);
}
