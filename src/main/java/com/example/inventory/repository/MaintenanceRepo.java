package com.example.inventory.repository;

import com.example.inventory.dto.MaintenanceDto;
import com.example.inventory.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepo extends JpaRepository<Maintenance,Long> {
}
