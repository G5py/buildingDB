package com.example.buildingdb.controller;

import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TagDto> postTag(@RequestBody TagDto tagDto) throws InvalidDataException, URISyntaxException {
        TagDto resultTagDto = tagService.addTag(tagDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/tags/" + resultTagDto.getId().toString()));

        return new ResponseEntity<>(resultTagDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto getTag(@PathVariable Long id) throws InvalidDataException {
        return tagService.getTag(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto putTag(@PathVariable Long id, @RequestBody TagDto tagDto) throws InvalidDataException {
        return tagService.putTag(id, tagDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto deleteTag(@PathVariable Long id) throws InvalidDataException {
        tagService.deleteTag(id);

        return TagDto.builder()
                .id(id)
                .build();
    }

}
