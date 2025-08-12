package com.epam.microservice.springmicroserviceepam.repository;

import com.epam.microservice.springmicroserviceepam.model.TrainerSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerSummaryRepository extends JpaRepository<TrainerSummary, Long> {
    Optional<TrainerSummary> findByUsername(String username);
}