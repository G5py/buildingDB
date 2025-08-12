package com.example.buildingdb.controller;


import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.ArchitectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/architects")
public class ArchitectController {

    private final ArchitectService architectService;

    @Autowired
    public ArchitectController(ArchitectService architectService) {
        this.architectService = architectService;
    }

    @PostMapping
    public ResponseEntity<ArchitectDto> postArchitect(@RequestBody ArchitectDto architectDto) throws InvalidDataException, URISyntaxException {
        ArchitectDto resultArchitectDto = architectService.addArchitect(architectDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/architects/" + resultArchitectDto.getId().toString()));

        return new ResponseEntity<>(resultArchitectDto, headers, HttpStatus.CREATED);
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
