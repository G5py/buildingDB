package com.example.buildingdb.service;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.entity.Building;
import com.example.buildingdb.entity.Category;
import com.example.buildingdb.entity.Tag;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.BuildingRepository;
import com.example.buildingdb.repository.CategoryRepository;
import com.example.buildingdb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BuildingRepository buildingRepository;
    private final TagRepository tagRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BuildingRepository buildingRepository, TagRepository tagRepository) {
        this.categoryRepository = categoryRepository;
        this.buildingRepository = buildingRepository;
        this.tagRepository = tagRepository;
    }

    public List<BuildingDto> getBuildingsByTagId(Long tagId) {
        validateId(tagId);

        Optional<Tag> tagOpt = tagRepository.findById(tagId);
        Tag tag = tagOpt.orElseThrow(() -> new InvalidDataException("Tag id is invalid."));

        List<Category> resultCategories = categoryRepository.findByTag(tag);

        return resultCategories.stream()
                .map(Category::getBuilding)
                .map(BuildingDto::new)
                .toList();
    }

    public List<TagDto> getTagsByBuildingId(Long buildingId) {
        validateId(buildingId);

        Optional<Building> buildingsOpt = buildingRepository.findById(buildingId);
        Building building = buildingsOpt.orElseThrow(() -> new InvalidDataException("Building id is invalid."));

        List<Category> resultCategories = categoryRepository.findByBuilding(building);

        return resultCategories.stream()
                .map(Category::getTag)
                .map(TagDto::new)
                .toList();
    }


    private void validateId(Long tagId) {
        if (tagId == null) {
            throw new InvalidDataException("Id can't be null.");
        }
    }
}
