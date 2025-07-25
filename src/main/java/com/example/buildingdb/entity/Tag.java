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
    private List<Category> category = new ArrayList<>();


    public List<Building> getBuildings() {
        return this.category.stream()
                .map(Category::getBuilding)
                .toList();
    }

    public void addCategory(Category category) {
        if (category == null) { return; }
        if (getCategory().contains(category)) { return; }

        this.getCategory().add(category);
    }


}
