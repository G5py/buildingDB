package com.example.buildingdb.controller;

import com.example.buildingdb.dto.TagBuildingResponse;
import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.service.CategoryService;
import com.example.buildingdb.service.TagService;
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
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final CategoryService categoryService;


    @Autowired
    public TagController(TagService tagService, CategoryService categoryService) {
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TagDto> postTag(@RequestBody TagDto tagDto) throws URISyntaxException {
        TagDto resultTagDto = tagService.addTag(tagDto);

        log.info("Tag, POST, Tag created. id : ".concat(resultTagDto.getId().toString()));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/tags/" + resultTagDto.getId().toString()));

        return new ResponseEntity<>(resultTagDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto getTag(@PathVariable Long id) {
        TagDto tagDto = tagService.getTag(id);

        log.info("Tag, GET, Tag requested. id : ".concat(tagDto.getId().toString()));

        return tagDto;
    }

    @GetMapping("/{tagId}/building")
    @ResponseStatus(HttpStatus.OK)
    public TagBuildingResponse getTaggedBuildings(@PathVariable Long tagId) {
        TagBuildingResponse buildingsByTag = categoryService.getBuildingsByTagId(tagId);

        log.info("Tag, GET, Buildings by tag requested. id : ".concat(tagId.toString()));

        return buildingsByTag;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto putTag(@PathVariable Long id, @RequestBody TagDto tagDto) {
        TagDto updatedTagDto = tagService.putTag(id, tagDto);

        log.info("Tag, PUT, Tag updated. id : ".concat(updatedTagDto.getId().toString()));

        return updatedTagDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);

        log.info("Tag, DELETE, Tag deleted. id : ".concat(id.toString()));

        return TagDto.builder()
                .id(id)
                .build();
    }

}
