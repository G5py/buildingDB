package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BUILDINGS_AND_TAGS")
@NoArgsConstructor
@Getter
@Setter
public class BuildingsAndTags {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    BuildingsAndTags(Building building, Tag tag) {
        this.building = building;
        this.tag = tag;
    }
}
