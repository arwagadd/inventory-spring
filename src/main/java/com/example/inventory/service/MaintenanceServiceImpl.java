package com.example.inventory.service;

import com.example.inventory.dto.MaintenanceDto;
import com.example.inventory.mapper.MaintenanceMapper;
import com.example.inventory.model.Maintenance;
import com.example.inventory.repository.MaintenanceRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepo maintenanceRepo;
    @Override
    public void setEndDate(Long id, LocalDateTime endTime) {
        Optional<Maintenance> maintenanceExists =  maintenanceRepo.findById(id);
        maintenanceExists.get().setEndTime(endTime);
        maintenanceRepo.save(maintenanceExists.get());
    }

//    private final MaintenanceRepo maintenanceRepo;
//    private final MaintenanceMapper maintenanceMapper;
//
//    @Transactional
//    @Override
//    public MaintenanceDto addMaintenance(MaintenanceDto maintenanceDto) {
//        Maintenance maintenance = maintenanceMapper.dtoToEntity(maintenanceDto);
//        maintenanceRepo.save(maintenance);
//        return maintenanceMapper.entityToDto(maintenance);
//    }

}
