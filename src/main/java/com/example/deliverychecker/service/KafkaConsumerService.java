package com.example.deliverychecker.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "delivery-topic", groupId = "delivery-group")
    public void listen(String message) {
        System.out.println("Received from Kafka: " + message);
    }
}
