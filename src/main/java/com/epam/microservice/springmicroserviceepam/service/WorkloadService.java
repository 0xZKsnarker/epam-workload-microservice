package com.epam.microservice.springmicroserviceepam.service;

import com.epam.microservice.springmicroserviceepam.dto.TrainerSummaryDto;
import com.epam.microservice.springmicroserviceepam.dto.WorkloadRequest;
import com.epam.microservice.springmicroserviceepam.model.MonthSummary;
import com.epam.microservice.springmicroserviceepam.model.TrainerSummary;
import com.epam.microservice.springmicroserviceepam.model.YearSummary;
import com.epam.microservice.springmicroserviceepam.repository.TrainerSummaryRepository;
import com.epam.microservice.springmicroserviceepam.util.TrainerSummaryMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkloadService {

    private static final Logger log = LoggerFactory.getLogger(WorkloadService.class);
    private final TrainerSummaryRepository trainerSummaryRepository;

    public WorkloadService(TrainerSummaryRepository trainerSummaryRepository) {
        this.trainerSummaryRepository = trainerSummaryRepository;
    }

    @Transactional
    public void updateWorkload(WorkloadRequest request) {
        log.info("Processing workload update for trainer: {}", request.getTrainerUsername());

        TrainerSummary summary = trainerSummaryRepository.findByUsername(request.getTrainerUsername())
                .orElseGet(() -> {
                    TrainerSummary newSummary = new TrainerSummary();
                    newSummary.setUsername(request.getTrainerUsername());
                    return newSummary;
                });

        summary.setFirstName(request.getTrainerFirstName());
        summary.setLastName(request.getTrainerLastName());
        summary.setActive(request.isActive());

        YearSummary yearSummary = summary.getYears().stream()
                .filter(y -> y.getYear() == request.getTrainingDate().getYear())
                .findFirst()
                .orElseGet(() -> {
                    YearSummary newYear = new YearSummary();
                    newYear.setYear(request.getTrainingDate().getYear());
                    newYear.setTrainerSummary(summary);
                    summary.getYears().add(newYear);
                    return newYear;
                });

        MonthSummary monthSummary = yearSummary.getMonths().stream()
                .filter(m -> m.getMonth() == request.getTrainingDate().getMonthValue())
                .findFirst()
                .orElseGet(() -> {
                    MonthSummary newMonth = new MonthSummary();
                    newMonth.setMonth(request.getTrainingDate().getMonthValue());
                    newMonth.setYearSummary(yearSummary);
                    yearSummary.getMonths().add(newMonth);
                    return newMonth;
                });

        int durationChange = "ADD".equalsIgnoreCase(request.getActionType()) ? request.getTrainingDuration() : -request.getTrainingDuration();
        monthSummary.setTrainingSummaryDuration(monthSummary.getTrainingSummaryDuration() + durationChange);

        trainerSummaryRepository.save(summary);
        log.info("Successfully updated workload for {}. Year: {}, Month: {}. New total duration: {}",
                request.getTrainerUsername(), yearSummary.getYear(), monthSummary.getMonth(), monthSummary.getTrainingSummaryDuration());
    }

    public TrainerSummaryDto getWorkload(String username) {
        return trainerSummaryRepository.findByUsername(username)
                .map(TrainerSummaryMapper::toDto)
                .orElse(null);
    }
}