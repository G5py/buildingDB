package com.example.buildingdb.dto;


import com.example.buildingdb.entity.Architect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchitectDto {
    private final boolean success = true;
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
