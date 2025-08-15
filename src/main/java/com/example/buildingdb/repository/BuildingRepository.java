package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Building;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    default Building findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Building is not found with such name."));
    }
}
