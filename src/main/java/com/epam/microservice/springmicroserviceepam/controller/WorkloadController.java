package com.epam.microservice.springmicroserviceepam.controller;

import com.epam.microservice.springmicroserviceepam.dto.WorkloadRequest;
import com.epam.microservice.springmicroserviceepam.model.TrainerSummary;
import com.epam.microservice.springmicroserviceepam.service.WorkloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workload")
public class WorkloadController {

    private final WorkloadService workloadService;

    public WorkloadController(WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    @PostMapping
    public ResponseEntity<Void> updateTrainerWorkload(@RequestBody WorkloadRequest request) {
        workloadService.updateWorkload(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<TrainerSummary> getTrainerWorkload(@PathVariable String username) {
        TrainerSummary summary = workloadService.getWorkload(username);
        if (summary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summary);
    }
}