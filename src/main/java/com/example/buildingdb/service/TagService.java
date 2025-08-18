package com.example.buildingdb.service;

import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.entity.Tag;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDto addTag(TagDto tagDto) {
        validateTagDto(tagDto);

        Tag newTag = tagRepository.save(tagDto.toTagEntity());
        return new TagDto(newTag);
    }

    public TagDto getTag(Long id) {
        validateId(id);

        Tag tag = tagRepository.findByIdOrThrow(id);
        return new TagDto(tag);
    }

    public TagDto putTag(Long id, TagDto tagDto) {
        validateId(id);
        validateTagDto(tagDto);

        Tag saved = tagRepository.save(tagDto.toTagEntity());

        return new TagDto(saved);
    }

    public void deleteTag(Long id) {
        validateId(id);
        if (!tagRepository.existsById(id)) {
            throw new InvalidDataException("Id is invalid.");
        }

        tagRepository.deleteById(id);
    }


    private void validateId(Long id) {
        if (id == null) {
            throw new InvalidDataException("Id can't be null.");
        }
    }

    private void validateTagDto(TagDto tagDto) {
        if (tagDto.getName() == null) {
            throw new InvalidDataException("Tag name can't be null.");
        }
    }
}
