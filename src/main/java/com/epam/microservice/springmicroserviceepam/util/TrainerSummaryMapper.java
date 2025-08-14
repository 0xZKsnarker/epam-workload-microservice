package com.epam.microservice.springmicroserviceepam.util;

import com.epam.microservice.springmicroserviceepam.dto.MonthSummaryDto;
import com.epam.microservice.springmicroserviceepam.dto.TrainerSummaryDto;
import com.epam.microservice.springmicroserviceepam.dto.YearSummaryDto;
import com.epam.microservice.springmicroserviceepam.model.MonthSummary;
import com.epam.microservice.springmicroserviceepam.model.TrainerSummary;
import com.epam.microservice.springmicroserviceepam.model.YearSummary;
import java.util.stream.Collectors;

public class TrainerSummaryMapper {

    public static TrainerSummaryDto toDto(TrainerSummary summary) {
        if (summary == null) {
            return null;
        }
        TrainerSummaryDto dto = new TrainerSummaryDto();
        dto.setUsername(summary.getUsername());
        dto.setFirstName(summary.getFirstName());
        dto.setLastName(summary.getLastName());
        dto.setActive(summary.isActive());
        dto.setYears(summary.getYears().stream()
                .map(TrainerSummaryMapper::toYearDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public static YearSummaryDto toYearDto(YearSummary yearSummary) {
        YearSummaryDto dto = new YearSummaryDto();
        dto.setYear(yearSummary.getYear());
        dto.setMonths(yearSummary.getMonths().stream()
                .map(TrainerSummaryMapper::toMonthDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public static MonthSummaryDto toMonthDto(MonthSummary monthSummary) {
        MonthSummaryDto dto = new MonthSummaryDto();
        dto.setMonth(monthSummary.getMonth());
        dto.setTrainingSummaryDuration(monthSummary.getTrainingSummaryDuration());
        return dto;
    }
}