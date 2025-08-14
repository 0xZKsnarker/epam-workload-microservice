package com.epam.microservice.springmicroserviceepam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MonthSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "training_month")
    private int month;
    private int trainingSummaryDuration = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_summary_id")
    @JsonIgnore
    private YearSummary yearSummary;
}