package com.example.buildingdb.service;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.entity.Building;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import com.example.buildingdb.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    private final ArchitectRepository architectRepository;
    private final BuildingRepository  buildingRepository;

    @Autowired
    public BuildingService(ArchitectRepository architectRepository, BuildingRepository buildingRepository) {
        this.architectRepository = architectRepository;
        this.buildingRepository  = buildingRepository;
    }

    public BuildingDto addBuilding(BuildingDto buildingDto) {
        ValidationUtil.validateBuildingDto(buildingDto);

        Architect architect = architectRepository.findByNameOrThrow(buildingDto.getArchitectName());
        return new BuildingDto(buildingRepository.save(buildingDto.toBuildingEntity(architect)));
    }

    public BuildingDto getBuilding(Long id) {
        ValidationUtil.validateId(id);

        Building building = buildingRepository.findByIdOrThrow(id);
        return new BuildingDto(building);
    }

    public BuildingDto putBuilding(Long id, BuildingDto buildingDto) {
        ValidationUtil.validateId(id);
        ValidationUtil.validateBuildingDto(buildingDto);

        Architect architect = architectRepository.findByNameOrThrow(buildingDto.getArchitectName());
        Building saved = buildingRepository.save(buildingDto.toBuildingEntity(architect));

        return new BuildingDto(saved);
    }

    public void deleteBuilding(Long id) {
        ValidationUtil.validateId(id);

        buildingRepository.deleteById(id);
    }
}
