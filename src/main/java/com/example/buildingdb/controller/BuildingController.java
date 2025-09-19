package com.example.buildingdb.controller;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.dto.BuildingTagResponse;
import com.example.buildingdb.service.BuildingService;
import com.example.buildingdb.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
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

        log.info("Building, POST, Building created. id : ".concat(resultBuildingDto.getId().toString()));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/buildings/" + resultBuildingDto.getId().toString()));

        return new ResponseEntity<>(resultBuildingDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto getBuilding(@PathVariable Long id) {
        BuildingDto buildingDto = buildingService.getBuilding(id);

        log.info("Building, Get, Building Requested. id : ".concat(buildingDto.getId().toString()));

        return buildingDto;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto putBuilding(@PathVariable Long id, @RequestBody BuildingDto buildingDto) {
        BuildingDto updatedBuildingDto = buildingService.putBuilding(id, buildingDto);

        log.info("Building, Put, Building updated. id : ".concat(updatedBuildingDto.getId().toString()));

        return updatedBuildingDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);

        log.info("Building, Delete, Building deleted. id : ".concat(id.toString()));

        return BuildingDto.builder()
                .id(id)
                .build();
    }


    @GetMapping("/{buildingId}/tag")
    @ResponseStatus(HttpStatus.OK)
    public BuildingTagResponse getBuildingTags(@PathVariable Long buildingId) {
        BuildingTagResponse buildingTag = categoryService.getTagsByBuildingId(buildingId);

        log.info("Building, GET, Tags on building requested. buildingId : ".concat(buildingId.toString()));

        return buildingTag;
    }

    @PostMapping("/{buildingId}/tag")
    public BuildingTagResponse postTagOnBuilding(@PathVariable Long buildingId, @RequestBody Long tagId) {
        BuildingTagResponse buildingTag = categoryService.addTagOnBuilding(buildingId, tagId);

        StringBuilder stringBuilder = new StringBuilder();
        log.info(stringBuilder.append("Building, POST, Tag set on building. buildingId : ")
                .append(buildingId.toString())
                .append(", tagId : ")
                .append(tagId.toString())
                .toString());
        
        return buildingTag;
    }

    @DeleteMapping("/{buildingId}/{tagId}")
    public BuildingTagResponse deleteTagOnBuilding(@PathVariable Long buildingId, @PathVariable Long tagId) {
        BuildingTagResponse buildingTagResponse = categoryService.untagBuilding(buildingId, tagId);

        StringBuilder stringBuilder = new StringBuilder();
        log.info(stringBuilder.append("Building, DELETE, Tag set on building. buildingId : ")
                .append(buildingId.toString())
                .append(", tagId : ")
                .append(tagId.toString())
                .toString());

        return buildingTagResponse;
    }
}
