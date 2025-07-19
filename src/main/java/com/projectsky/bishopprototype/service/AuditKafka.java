package com.projectsky.bishopprototype.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditKafka {

    @KafkaListener(
            topics = "${synthetic.human.audit.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(String message) {
        log.info("FROM KAFKA: {}", message);
    }
}
