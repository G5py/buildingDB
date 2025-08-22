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
        ValidationUtil.validateId(tagId);

        Tag tag = tagRepository.findByIdOrThrow(tagId);

        List<Category> resultCategories = categoryRepository.findByTag(tag);

        return resultCategories.stream()
                .map(Category::getBuilding)
                .map(BuildingDto::new)
                .toList();
    }

    public List<TagDto> getTagsByBuildingId(Long buildingId) {
        ValidationUtil.validateId(buildingId);

        Building building = buildingRepository.findByIdOrThrow(buildingId);

        List<Category> resultCategories = categoryRepository.findByBuilding(building);

        return resultCategories.stream()
                .map(Category::getTag)
                .map(TagDto::new)
                .toList();
    }

    public void addTagOnBuilding(Long buildingId, Long tagId) {
        ValidationUtil.validateId(buildingId);
        ValidationUtil.validateId(tagId);
        validateCategoryExistence(buildingId, tagId);

        Building building = buildingRepository.findByIdOrThrow(buildingId);
        Tag tag = tagRepository.findByIdOrThrow(tagId);

        Category category = new Category(null, building, tag);
        categoryRepository.save(category);
    }


    private void validateCategoryExistence(Long buildingId, Long tagId) {
        if (existsCategory(buildingId, tagId)) {
            throw new InvalidDataException("The tag is already set on the building.");
        }
    }

    private boolean existsCategory(Long buildingId, Long tagId) {
        return categoryRepository.existsByBuildingIdAndTagId(buildingId, tagId);
    }
}
