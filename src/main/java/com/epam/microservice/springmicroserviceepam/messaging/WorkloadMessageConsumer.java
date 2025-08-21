package com.epam.microservice.springmicroserviceepam.messaging;

import com.epam.microservice.springmicroserviceepam.config.JmsConfig;
import com.epam.microservice.springmicroserviceepam.dto.WorkloadRequest;
import com.epam.microservice.springmicroserviceepam.service.WorkloadService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkloadMessageConsumer {

    @Autowired
    private WorkloadService workloadService;

    @JmsListener(destination = JmsConfig.WORKLOAD_QUEUE)
    public void receiveWorkloadUpdate(
            @Payload WorkloadRequest request,
            @Header(value = "transactionId", required = false) String transactionId,
            @Header(value = "Authorization", required = false) String authorization) {

        try {
            // Set transaction ID in MDC for logging
            if (transactionId != null) {
                MDC.put("transactionId", transactionId);
            }

            log.info("Received workload update from queue for trainer: {} with transaction ID: {}",
                    request.getTrainerUsername(), transactionId);

            // Process the workload update
            workloadService.updateWorkload(request);

            log.info("Successfully processed workload update for trainer: {}", request.getTrainerUsername());

        } catch (Exception e) {
            log.error("Error processing workload update for trainer: {}", request.getTrainerUsername(), e);
            // In production, you'd send this to a DLQ or retry
            throw e;
        } finally {
            MDC.clear();
        }
    }
}