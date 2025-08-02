package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Architect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchitectRepository extends JpaRepository<Architect, Long> {
    Optional<Architect> findByName(String name);

    boolean existsByName(String name);

    boolean existsById(Long id);
}
