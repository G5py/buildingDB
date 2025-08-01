package com.example.buildingdb.service;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


// TODO : CRUD 다 만들기.
@Service
public class ArchitectService {
    private final ArchitectRepository architectRepo;

    @Autowired
    public ArchitectService(ArchitectRepository architectRepo) {
        this.architectRepo = architectRepo;
    }

    public void addArchitect(ArchitectDto architectDto) throws InvalidDataException {
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

        architectRepo.save(architectDto.toArchitectEntity());
    }

    private boolean isValidEnglishName(String englishName) {
        return Pattern.matches("^[a-zA-Z\\s]+$", englishName);
    }

    private boolean isValidKoreanName(String koreanName) {
        return Pattern.matches("^[가-힣0-9\\s]+$", koreanName);
    }
}
