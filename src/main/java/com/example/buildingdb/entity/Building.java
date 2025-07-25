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
    private List<BuildingsAndTags> buildingsAndTags = new ArrayList<>();


    public List<Tag> getTags() {
        return this.buildingsAndTags.stream()
                .map(BuildingsAndTags::getTag)
                .toList();
    }

    public void addTag(Tag tag) {
        if (tag == null) { return; }
        if (getTags().contains(tag)) { return; }

        this.buildingsAndTags.add(new BuildingsAndTags(this, tag));
    }
}
