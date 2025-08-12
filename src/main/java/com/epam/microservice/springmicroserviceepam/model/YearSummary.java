package com.epam.microservice.springmicroserviceepam.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class YearSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "training_yearh")
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_summary_id")
    private TrainerSummary trainerSummary;

    @OneToMany(mappedBy = "yearSummary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthSummary> months = new ArrayList<>();
}