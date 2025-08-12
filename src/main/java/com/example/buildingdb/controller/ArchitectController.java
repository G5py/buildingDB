package com.example.buildingdb.controller;


import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.ArchitectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ArchitectDto postArchitect(@RequestBody ArchitectDto architectDto) throws InvalidDataException {
        return architectService.addArchitect(architectDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArchitectDto getArchitect(@PathVariable Long id) throws InvalidDataException {
        return architectService.getArchitect(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArchitectDto putArchitect(@PathVariable Long id, @RequestBody ArchitectDto architectDto) throws InvalidDataException {
        return architectService.putArchitect(id, architectDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArchitectDto deleteArchitect(@PathVariable Long id) throws InvalidDataException {
        architectService.deleteArchitect(id);
        return ArchitectDto.builder().id(id).build();
    }
}
