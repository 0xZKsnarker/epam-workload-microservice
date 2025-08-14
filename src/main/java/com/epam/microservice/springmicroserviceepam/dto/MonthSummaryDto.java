package com.epam.microservice.springmicroserviceepam.dto;

import lombok.Data;

@Data
public class MonthSummaryDto {
    private int month;
    private int trainingSummaryDuration;
}