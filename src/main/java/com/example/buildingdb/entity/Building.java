package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;


@Entity
@Table(name = "BUILDINGS")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String koreanName;

    @Column(columnDefinition = "POINT")
    private Point coordinates;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @ManyToOne
    @JoinColumn(name = "architect_id")
    private Architect architect;


    public Building(@NonNull String name, Architect architect) {
        this.name = name;
        this.architect = architect;
    }

}
