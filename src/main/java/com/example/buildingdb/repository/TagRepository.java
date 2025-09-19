package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    default Tag findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Tag is not found with such id. id : ".concat(id.toString())));
    }
}
