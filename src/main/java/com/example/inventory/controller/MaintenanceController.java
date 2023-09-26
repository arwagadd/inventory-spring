package com.example.inventory.controller;

import com.example.inventory.service.MaintenanceService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api/maintenance")
@AllArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @PutMapping(path = "/setEndDate/{id}/{endDate}")
    public void setEndDate(@PathVariable("id") Long id, @PathVariable("endDate") LocalDateTime endDate){
        maintenanceService.setEndDate(id,endDate);
    }
}
