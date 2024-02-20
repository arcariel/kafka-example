package com.arcariel.springboot.kafka.service;

import com.arcariel.springboot.kafka.entity.WikimediaData;
import com.arcariel.springboot.kafka.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    @Autowired
    private WikimediaRepository repository;

    @KafkaListener(topics = "wikimedia_recentchanges", groupId = "java-application")
    public void consume(String eventMessage) {
        LOG.info(String.format("Message received: %s", eventMessage));
        WikimediaData data = new WikimediaData();
        data.setData(eventMessage);

        repository.save(data);
    }
}
