package com.example.buildingdb.service;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// TODO : CRUD 다 만들기.
// TODO : 테스트 코드 추가 필요.
@Service
public class ArchitectService {
    private final ArchitectRepository architectRepo;

    @Autowired
    public ArchitectService(ArchitectRepository architectRepo) {
        this.architectRepo = architectRepo;
    }

    // TODO : ArchitectDto.name에 영어가 아닌 문자가 있는지 검사하는 구문 필요.
    // TODO : ArchitectDto.koreanName에 한글과 숫자가 아닌 문자가 있는지 검사하는 구문 필요.
    public void addArchitect(ArchitectDto architectDto) throws InvalidDataException {
        if (architectDto.getName() == null) {
            throw new InvalidDataException("Architect's name can't be null.");
        }


        architectRepo.save(architectDto.toArchitectEntity());
    }
}
