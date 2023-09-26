package com.example.inventory.service;

import com.example.inventory.dto.MaintenanceDto;

import java.time.LocalDateTime;

public interface MaintenanceService {
    void setEndDate(Long id, LocalDateTime endTime);
}
