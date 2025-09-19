package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Architect;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchitectRepository extends JpaRepository<Architect, Long> {
    Optional<Architect> findByName(String name);

    default Architect findByNameOrThrow(String name) {
        return findByName(name).orElseThrow(() -> new EntityNotFoundException("Architect is not found with such name. name : ".concat(name)));
    }

    default Architect findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Architect is not found with such id. id : ".concat(id.toString())));
    }

    boolean existsByName(String name);

    boolean existsById(@NonNull Long id);
}
