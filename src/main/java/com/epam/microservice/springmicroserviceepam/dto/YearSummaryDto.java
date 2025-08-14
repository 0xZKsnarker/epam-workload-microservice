package com.epam.microservice.springmicroserviceepam.dto;

import lombok.Data;
import java.util.List;

@Data
public class YearSummaryDto {
    private int year;
    private List<MonthSummaryDto> months;
}