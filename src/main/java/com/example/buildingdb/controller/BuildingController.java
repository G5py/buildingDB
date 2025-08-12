package com.example.buildingdb.controller;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BuildingDto postBuilding(@RequestBody BuildingDto buildingDto) throws InvalidDataException {
        return buildingService.addBuilding(buildingDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto getBuilding(@PathVariable Long id) throws InvalidDataException {
        return buildingService.getBuilding(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto putBuilding(@PathVariable Long id, @RequestBody BuildingDto buildingDto) throws InvalidDataException {
        return buildingService.putBuilding(id, buildingDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingDto deleteBuilding(@PathVariable Long id) throws InvalidDataException {
        buildingService.deleteBuilding(id);
        return BuildingDto.builder()
                .id(id)
                .build();
    }
}
