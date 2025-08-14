package com.example.buildingdb.controller;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.service.BuildingService;
import com.example.buildingdb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService buildingService;
    private final CategoryService categoryService;


    @Autowired
    public BuildingController(BuildingService buildingService, CategoryService categoryService) {
        this.buildingService = buildingService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<BuildingDto> postBuilding(@RequestBody BuildingDto buildingDto) throws URISyntaxException {
        BuildingDto resultBuildingDto = buildingService.addBuilding(buildingDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/buildings/" + resultBuildingDto.getId().toString()));

        return new ResponseEntity<>(resultBuildingDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto getBuilding(@PathVariable Long id) {
        return buildingService.getBuilding(id);
    }

    @GetMapping("/{id}/tag")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDto> getBuildingTags(@PathVariable Long id) {
        return categoryService.getTagsByBuildingId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto putBuilding(@PathVariable Long id, @RequestBody BuildingDto buildingDto) {
        return buildingService.putBuilding(id, buildingDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return BuildingDto.builder()
                .id(id)
                .build();
    }
}
