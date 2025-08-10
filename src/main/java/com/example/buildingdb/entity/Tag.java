package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "TAGS")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
}
