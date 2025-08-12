package com.epam.microservice.springmicroserviceepam.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TrainerSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;

    @OneToMany(mappedBy = "trainerSummary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<YearSummary> years = new ArrayList<>();
}