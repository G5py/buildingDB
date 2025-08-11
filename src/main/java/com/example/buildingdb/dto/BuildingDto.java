package com.example.buildingdb.dto;

import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.entity.Building;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class BuildingDto {

    private final boolean success = true;
    private Long id;
    private String name;
    private String koreanName;
    private double coordinateX;
    private double coordinateY;
    private LocalDate completeDate;
    private String architectName;


    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.koreanName = building.getKoreanName();
        this.coordinateX = building.getCoordinates().getX();
        this.coordinateY = building.getCoordinates().getY();
        this.completeDate = building.getCompletedDate();
        this.architectName = building.getArchitect().getName();

    }

    public Building toBuildingEntity(Architect architect) {
        GeometryFactory geoFac = new GeometryFactory();

        return Building.builder()
                .name(this.name)
                .koreanName(this.name)
                .coordinates(geoFac.createPoint(new Coordinate(this.coordinateX, this.coordinateY)))
                .completedDate(this.completeDate)
                .architect(architect)
                .build();
    }

}
