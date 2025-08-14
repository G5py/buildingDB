package com.example.buildingdb.service;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.entity.Building;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import com.example.buildingdb.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (buildingDto.getName() == null) {
            throw new InvalidDataException("Building's name can't be null.");
        }
        if (buildingDto.getArchitectName() == null) {
            throw new InvalidDataException("Architect name can't be null.");
        }

        Optional<Architect> architectOpt = architectRepository.findByName(buildingDto.getArchitectName());
        Architect architect = architectOpt.orElseThrow(() -> new InvalidDataException("Architect name is invalid."));

        return new BuildingDto(buildingRepository.save(buildingDto.toBuildingEntity(architect)));
    }

    public BuildingDto getBuilding(Long id) {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }

        Building building = buildingRepository.findById(id).orElseThrow(() -> new InvalidDataException("Id is invalid."));

        return new BuildingDto(building);
    }

    public BuildingDto putBuilding(Long id, BuildingDto buildingDto) {
        if (id == null) {
            throw new InvalidDataException("Id can't be null");
        }
        if (buildingDto.getArchitectName() == null) {
            throw new InvalidDataException("Architect name can't be null.");
        }

        Optional<Architect> architectOpt = architectRepository.findByName(buildingDto.getArchitectName());
        Architect architect = architectOpt.orElseThrow(() -> new InvalidDataException("Architect's name is invalid."));

        Building saved = buildingRepository.save(buildingDto.toBuildingEntity(architect));

        return new BuildingDto(saved);
    }

    public void deleteBuilding(Long id) {
        if (id == null) {
            throw new InvalidDataException("Id can't be null");
        }

        buildingRepository.deleteById(id);
    }

}
