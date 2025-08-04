package com.example.buildingdb.controller;


import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.ArchitectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/architects")
public class ArchitectController {

    private ArchitectService architectService;

    @Autowired
    public ArchitectController(ArchitectService architectService) {
        this.architectService = architectService;
    }

    @GetMapping("/{id}")
    public ArchitectDto getArchitect(@PathVariable Long id) throws InvalidDataException{
        try {
            return architectService.getArchitect(id);
        } catch (InvalidDataException e) {
            throw new InvalidDataException(e.getMessage());
        }

    }

}
