package com.example.buildingdb.controller;

import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.entity.Tag;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public TagDto postTag(@RequestBody TagDto tagDto) throws InvalidDataException {
        return tagService.addTag(tagDto);
    }

    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable Long id) throws InvalidDataException {
        return tagService.getTag(id);
    }

    @PutMapping("/{id}")
    public TagDto putTag(@PathVariable Long id, @RequestBody TagDto tagDto) throws InvalidDataException {
        return tagService.putTag(id, tagDto);
    }

    @DeleteMapping("/{id}")
    public TagDto deleteTag(@PathVariable Long id) throws InvalidDataException {
        tagService.deleteTag(id);

        return TagDto.builder()
                .id(id)
                .build();
    }

}
