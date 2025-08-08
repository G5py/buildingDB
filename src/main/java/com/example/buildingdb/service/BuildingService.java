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

    public BuildingDto addBuilding(BuildingDto buildingDto) throws InvalidDataException {
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

    public BuildingDto getBuilding(Long id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }

        Building building = buildingRepository.findById(id).orElseThrow(() -> new InvalidDataException("Id is invalid."));

        return new BuildingDto(building);
    }

    public BuildingDto putBuilding(BuildingDto buildingDto) throws InvalidDataException {
        if (buildingDto.getId() == null) {
            throw new InvalidDataException("Id can't be null");
        }
        if (!buildingRepository.existsById(buildingDto.getId())) {
            throw new InvalidDataException("Id is invalid.");
        }
        if (buildingDto.getArchitectName() == null) {
            throw new InvalidDataException("Architect name can't be null.");
        }

        Optional<Architect> architectOpt = architectRepository.findByName(buildingDto.getArchitectName());
        Architect architect = architectOpt.orElseThrow(() -> new InvalidDataException("Architect's name is invalid."));

        return new BuildingDto(buildingRepository.save(buildingDto.toBuildingEntity(architect)));
    }

    public void deleteBuilding(Long id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("id can't be null");
        }

        buildingRepository.deleteById(id);
    }

}
