package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "BUILDINGS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "POINT")
    private Point coordinates;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @ManyToOne
    @JoinColumn(name = "architect_id")
    private Architect architect;

    @OneToMany(mappedBy = "building")
    private List<Category> category = new ArrayList<>();


    public List<Tag> getTags() {
        return this.category.stream()
                .map(Category::getTag)
                .toList();
    }

    public void addCategory(Category category) {
        if (category == null) { return; }
        if (getCategory().contains(category)) { return; }

        this.category.add(category);
    }
}
