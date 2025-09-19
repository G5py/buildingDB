package com.example.buildingdb.service;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import com.example.buildingdb.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArchitectService {
    private final ArchitectRepository architectRepository;

    @Autowired
    public ArchitectService(ArchitectRepository architectRepository) {
        this.architectRepository = architectRepository;
    }

    public ArchitectDto addArchitect(ArchitectDto architectDto)  {
        ValidationUtil.validateArchitectDto(architectDto);

        if (architectRepository.existsByName(architectDto.getName())) {
            throw new InvalidDataException("Architect's name already exists. architectName: ".concat(architectDto.getName()));
        }

        return new ArchitectDto(architectRepository.save(architectDto.toArchitectEntity()));

    }

    public ArchitectDto getArchitect(Long id) {
        ValidationUtil.validateId(id);

        Architect architect = architectRepository.findByIdOrThrow(id);
        return new ArchitectDto(architect);
    }

    public ArchitectDto putArchitect(Long id, ArchitectDto architectDto) {
        ValidationUtil.validateId(id);
        ValidationUtil.validateArchitectDto(architectDto);

        Architect saved = architectRepository.save(architectDto.toArchitectEntity());

        return new ArchitectDto(saved);
    }

    public void deleteArchitect(Long id) {
        ValidationUtil.validateId(id);

        architectRepository.deleteById(id);
    }
}
