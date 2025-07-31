package com.example.buildingdb.dto;


import com.example.buildingdb.entity.Architect;
import lombok.Data;

@Data
public class ArchitectDto {

    private String name;
    private String koreanName;

    public Architect toArchitectEntity() {
        return new Architect(this.name, this.koreanName);
    }
}
