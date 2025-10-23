package com.example.buildingdb.dto;

import com.example.buildingdb.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TagDto implements DtoInterface {

    private final boolean success = true;
    private Long id;
    private String name;

    public TagDto(Tag tagEntity) {
        this.id = tagEntity.getId();
        this.name = tagEntity.getName();
    }

    public Tag toTagEntity() {
        return new Tag(this.name);
    }
}
