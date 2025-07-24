package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "ARCHITECTS")
@Getter
public class Architect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
