package com.example.buildingdb.repository;

import com.example.buildingdb.entity.Category;
import com.example.buildingdb.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByTag(Tag tag);
}
