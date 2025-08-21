package com.epam.microservice.springmicroserviceepam.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkloadDLQHandler {

    @JmsListener(destination = "DLQ.trainer-workload-queue")
    public void handleFailedMessage(String message) {
        log.error("message failed", message);
    }
}