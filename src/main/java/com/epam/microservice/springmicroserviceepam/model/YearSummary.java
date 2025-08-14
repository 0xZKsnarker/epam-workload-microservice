package com.epam.microservice.springmicroserviceepam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "training_year")
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_summary_id")
    @JsonIgnore
    private TrainerSummary trainerSummary;

    @OneToMany(mappedBy = "yearSummary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthSummary> months = new ArrayList<>();
}