package com.example.buildingdb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class BuildingTagResponse {
    private final boolean success = true;

    private Long buildingId;
    private String buildingName;
    private List<TagDto> tags;

}
