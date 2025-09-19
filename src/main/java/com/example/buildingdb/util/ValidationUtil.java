package com.example.buildingdb.util;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.exception.InvalidDataException;

import java.util.regex.Pattern;

public class ValidationUtil {
    public static void validateIdNull(Long id) {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }
    }

    public static void validateId(Long id) {
        validateIdNull(id);
    }

    public static void validateArchitectDto(ArchitectDto dto) {
        String name = dto.getName();

        if (name == null) {
            throw new InvalidDataException("Architect's name can't be null.");
        }
        if (!isValidArchitectEnglishName(name)) {
            throw new InvalidDataException("Architect's name contains non-english characters. name : ".concat(name));
        }

        String koreanName = dto.getKoreanName();

        if (koreanName != null &&
                !isValidArchitectKoreanName(koreanName)) {
            throw new InvalidDataException("Architect's korean name is invalid. koreanName : ".concat(koreanName));
        }
    }

    public static void validateArchitectName(String architectName) {
        if (architectName == null) {
            throw new InvalidDataException("Architect name can't be null.");
        }
    }

    public static void validateBuildingName(String buildingName) {
        if (buildingName == null) {
            throw new InvalidDataException("Building's name can't be null.");
        }
    }

    public static void validateTagDto(TagDto tagDto) {
        if (tagDto.getName() == null) {
            throw new InvalidDataException("Tag name can't be null.");
        }
    }

    public static void validateBuildingDto(BuildingDto buildingDto) {
        validateBuildingName(buildingDto.getName());
        validateArchitectName(buildingDto.getArchitectName());
    }


    public static boolean isValidArchitectEnglishName(String englishName) {
        return Pattern.matches("^[a-zA-Z\\s]+$", englishName);
    }

    public static boolean isValidArchitectKoreanName(String koreanName) {
        return Pattern.matches("^[가-힣0-9\\s]+$", koreanName);
    }
}
