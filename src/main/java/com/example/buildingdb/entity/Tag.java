package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TAGS")
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<BuildingsAndTags> buildingsAndTags = new ArrayList<>();


    public List<Building> getBuildings() {
        return this.buildingsAndTags.stream()
                .map(BuildingsAndTags::getBuilding)
                .toList();
    }

    public void addBuilding(Building building) {
        if (building == null) { return; }
        if (getBuildings().contains(building)) { return; }

        this.getBuildingsAndTags().add(new BuildingsAndTags(building, this));
    }


}
