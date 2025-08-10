package com.example.buildingdb.service;

import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.entity.Tag;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDto addTag(TagDto tagDto) throws InvalidDataException {
        if (tagDto.getName() == null) {
            throw new InvalidDataException("Tag name can't be null.");
        }

        Tag newTag = tagRepository.save(tagDto.toTagEntity());

        return new TagDto(newTag);
    }

    public TagDto getTag(Long id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }

        Optional<Tag> tagOpt = tagRepository.findById(id);
        Tag tag = tagOpt.orElseThrow(() -> new InvalidDataException("Id is invalid."));

        return new TagDto(tag);
    }

    public void deleteTag(Long id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }
        if (!tagRepository.existsById(id)) {
            throw new InvalidDataException("Id is invalid.");
        }

        tagRepository.deleteById(id);
    }

}
