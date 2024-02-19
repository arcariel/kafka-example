package com.arcariel.kafka.service;

import com.arcariel.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, User> template;

    public KafkaProducer(KafkaTemplate<String, User> template) {
        this.template = template;
    }

    public void sendMessage(User userMessage) {

        LOG.info(String.format("Sending User message to kafka: %s", userMessage.toString()));

        Message<User> message = MessageBuilder
                .withPayload(userMessage)
                .setHeader(KafkaHeaders.TOPIC, "demo_java-json")
                .build();
        this.template.send(message);
    }
}
