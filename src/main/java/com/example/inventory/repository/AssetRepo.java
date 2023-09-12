package com.example.inventory.repository;

import com.example.inventory.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Integer> {
}
