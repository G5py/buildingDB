package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Architect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchitectRepository extends JpaRepository<Architect, Long> {
}
