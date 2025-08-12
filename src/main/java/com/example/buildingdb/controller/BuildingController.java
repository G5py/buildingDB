package com.example.buildingdb.controller;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping
    public ResponseEntity<BuildingDto> postBuilding(@RequestBody BuildingDto buildingDto) throws InvalidDataException, URISyntaxException {
        BuildingDto resultBuildingDto = buildingService.addBuilding(buildingDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/buildings/" + resultBuildingDto.getId().toString()));

        return new ResponseEntity<>(resultBuildingDto, headers, HttpStatus.CREATED);
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
