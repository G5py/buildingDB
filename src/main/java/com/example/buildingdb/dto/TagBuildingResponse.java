package com.example.buildingdb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TagBuildingResponse {
    private final boolean success = true;

    private String tagName;
    private Long tagId;
    private List<BuildingDto> buildings;
}
