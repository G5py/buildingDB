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
public class ArchitectDto implements DtoInterface {
    private final boolean success = true;
    private Long id;
    private String name;
    private String koreanName;

    public ArchitectDto(Architect architect) {
        this.id = architect.getId();
        this.name = architect.getName();
        this.koreanName = architect.getKoreanName();
    }

    public Architect toArchitectEntity() {
        return new Architect(this.name, this.koreanName);
    }

}
