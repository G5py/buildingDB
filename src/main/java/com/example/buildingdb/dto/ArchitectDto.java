package com.example.buildingdb.dto;


import com.example.buildingdb.entity.Architect;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArchitectDto {

    private Long id;
    private String name;
    private String koreanName;

    public Architect toArchitectEntity() {
        return new Architect(this.name, this.koreanName);
    }

    public static ArchitectDto getFromArchitect(Architect architect) {
        return new ArchitectDto(architect.getId(), architect.getName(), architect.getKoreanName());
    }
}
