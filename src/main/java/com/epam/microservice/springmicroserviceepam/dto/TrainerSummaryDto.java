package com.epam.microservice.springmicroserviceepam.dto;

import lombok.Data;
import java.util.List;

@Data
public class TrainerSummaryDto {
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private List<YearSummaryDto> years;
}