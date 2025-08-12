package com.epam.microservice.springmicroserviceepam.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class WorkloadRequest {
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private boolean isActive;
    private LocalDate trainingDate;
    private int trainingDuration;
    private String actionType;
}