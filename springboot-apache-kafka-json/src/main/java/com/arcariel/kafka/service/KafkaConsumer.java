package com.arcariel.kafka.service;

import com.arcariel.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "demo_java-json", groupId = "java-application-json")
    public void consume(User message) {
        LOG.info(String.format("Message received: %s", message.toString()));
    }
}
