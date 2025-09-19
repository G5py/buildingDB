package com.example.buildingdb.controller;


import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.service.ArchitectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/architects")
public class ArchitectController {

    private final ArchitectService architectService;

    @Autowired
    public ArchitectController(ArchitectService architectService) {
        this.architectService = architectService;
    }

    @PostMapping
    public ResponseEntity<ArchitectDto> postArchitect(@RequestBody ArchitectDto architectDto) throws URISyntaxException {
        ArchitectDto resultArchitectDto = architectService.addArchitect(architectDto);

        log.info("Architect, POST, Architect created. id : ".concat(resultArchitectDto.getId().toString()));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/architects/".concat(resultArchitectDto.getId().toString())));

        return new ResponseEntity<>(resultArchitectDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArchitectDto getArchitect(@PathVariable Long id) {
        ArchitectDto architectDto = architectService.getArchitect(id);

        log.info("Architect, GET, Architect requested. id : ".concat(id.toString()));

        return architectDto;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArchitectDto putArchitect(@PathVariable Long id, @RequestBody ArchitectDto architectDto) {
        ArchitectDto updatedArchitectDto = architectService.putArchitect(id, architectDto);

        log.info("Architect, PUT, Architect updated. id : ".concat(id.toString()));

        return updatedArchitectDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArchitectDto deleteArchitect(@PathVariable Long id) {
        architectService.deleteArchitect(id);

        log.info("Architect, DELETE, Architect deleted. id : ".concat(id.toString()));

        return ArchitectDto.builder().id(id).build();
    }
}
