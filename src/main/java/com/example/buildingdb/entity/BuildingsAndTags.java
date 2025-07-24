package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "BUILDINGS_AND_TAGS")
@Getter
public class BuildingsAndTags {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;


}
