package com.example.buildingdb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ARCHITECTS")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Architect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
}
