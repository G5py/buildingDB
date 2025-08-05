package com.example.buildingdb.service;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class ArchitectService {
    private final ArchitectRepository architectRepo;

    @Autowired
    public ArchitectService(ArchitectRepository architectRepo) {
        this.architectRepo = architectRepo;
    }

    public ArchitectDto addArchitect(ArchitectDto architectDto) throws InvalidDataException {
        if (architectDto.getName() == null) {
            throw new InvalidDataException("Architect's name can't be null.");
        }
        if (!isValidEnglishName(architectDto.getName())) {
            throw new InvalidDataException("Architect's name contains non-english characters.");
        }
        if (architectDto.getKoreanName() != null &&
                !isValidKoreanName(architectDto.getKoreanName())) {
            throw new InvalidDataException("Architect's korean name is invalid.");
        }
        if (architectRepo.existsByName(architectDto.getName())) {
            throw new InvalidDataException("The architect's name already exists.");
        }

        return ArchitectDto.getFromArchitect(architectRepo.save(architectDto.toArchitectEntity()));

    }

    public ArchitectDto getArchitect(Long id) throws InvalidDataException{
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }

        Optional<Architect> archOpt = architectRepo.findById(id);
        Architect architect = archOpt.orElseThrow(() -> new InvalidDataException("Invalid architect id."));

        return ArchitectDto.getFromArchitect(architect);
    }

    public ArchitectDto putArchitect(ArchitectDto architectDto) throws InvalidDataException {
        if (architectDto.getId() == null) {
            throw new InvalidDataException("Id can't be null.");
        }
        if (architectRepo.existsById(architectDto.getId())) {
            throw new InvalidDataException("Invalid architect id.");
        }

        return ArchitectDto.getFromArchitect(architectRepo.save(architectDto.toArchitectEntity()));
    }

    public void deleteArchitect(Long id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }

        architectRepo.deleteById(id);
    }

    private boolean isValidEnglishName(String englishName) {
        return Pattern.matches("^[a-zA-Z\\s]+$", englishName);
    }

    private boolean isValidKoreanName(String koreanName) {
        return Pattern.matches("^[가-힣0-9\\s]+$", koreanName);
    }
}
