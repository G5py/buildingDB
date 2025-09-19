package com.example.buildingdb.service;

import com.example.buildingdb.dto.TagDto;
import com.example.buildingdb.entity.Tag;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.TagRepository;
import com.example.buildingdb.util.ValidationUtil;
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
        ValidationUtil.validateTagDto(tagDto);

        Tag newTag = tagRepository.save(tagDto.toTagEntity());
        return new TagDto(newTag);
    }

    public TagDto getTag(Long id) {
        ValidationUtil.validateId(id);

        Tag tag = tagRepository.findByIdOrThrow(id);
        return new TagDto(tag);
    }

    public TagDto putTag(Long id, TagDto tagDto) {
        ValidationUtil.validateId(id);
        ValidationUtil.validateTagDto(tagDto);

        Tag saved = tagRepository.save(tagDto.toTagEntity());

        return new TagDto(saved);
    }

    public void deleteTag(Long id) {
        ValidationUtil.validateId(id);
        validateTagExistenceById(id);

        tagRepository.deleteById(id);
    }

    private void validateTagExistenceById(Long id) {
        if (!existsTag(id)) {
            throw new InvalidDataException("Id is invalid. tagId: ".concat(id.toString()));
        }
    }

    private boolean existsTag(Long id) {
        return !tagRepository.existsById(id);
    }
}
