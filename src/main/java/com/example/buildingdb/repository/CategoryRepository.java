package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Building;
import com.example.buildingdb.entity.Category;
import com.example.buildingdb.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByTag(Tag tag);

    List<Category> findByBuilding(Building building);

    boolean existsByBuildingAndTag(Building building, Tag tag);
    boolean existsByBuildingIdAndTagId(Long buildingId, Long tagId);
}
