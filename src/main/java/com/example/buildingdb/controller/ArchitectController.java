package com.example.buildingdb.controller;


import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.ArchitectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/architects")
public class ArchitectController {

    private final ArchitectService architectService;

    @Autowired
    public ArchitectController(ArchitectService architectService) {
        this.architectService = architectService;
    }

    @PostMapping
    public ArchitectDto postArchitect(@RequestBody ArchitectDto architectDto) throws InvalidDataException {
        return architectService.addArchitect(architectDto);
    }


    @GetMapping("/{id}")
    public ArchitectDto getArchitect(@PathVariable Long id) throws InvalidDataException {
        return architectService.getArchitect(id);
    }

    @PutMapping
    public void putArchitect(@RequestBody ArchitectDto architectDto) throws InvalidDataException {
        architectService.putArchitect(architectDto);
    }

    @DeleteMapping("/{id}")
    public void deleteArchitect(@PathVariable Long id) throws InvalidDataException {
        architectService.deleteArchitect(id);
    }
}
