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
    private List<BuildingsAndTags> buildings = new ArrayList<>();


    public List<Building> getBuildings() {
        return this.buildings.stream()
                .map(BuildingsAndTags::getBuilding)
                .toList();
    }


}
