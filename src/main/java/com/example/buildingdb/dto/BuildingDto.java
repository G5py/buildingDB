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
    private Double coordinateX;
    private Double coordinateY;
    private LocalDate completeDate;
    private String architectName;


    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.koreanName = building.getKoreanName();
        this.coordinateX = null;
        this.coordinateY = null;
        this.completeDate = building.getCompletedDate();
        this.architectName = building.getArchitect().getName();

        if (building.getCoordinates() != null) {
            this.coordinateX = building.getCoordinates().getX();
            this.coordinateY = building.getCoordinates().getY();
        }
    }

    public Building toBuildingEntity(Architect architect) {
        GeometryFactory geoFac = new GeometryFactory();

        Building building = Building.builder()
                .name(this.name)
                .koreanName(this.name)
                .coordinates(null)
                .completedDate(this.completeDate)
                .architect(architect)
                .build();

        if (!isCoordinatesNull()) {
            building.setCoordinates(geoFac.createPoint(new Coordinate(this.coordinateX, this.coordinateY)));
        }

        return building;
    }

    private boolean isCoordinatesNull() {
        return this.coordinateX == null || this.coordinateY == null;
    }

}
