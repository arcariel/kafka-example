package com.arcariel.kafka.controller;

import com.arcariel.kafka.model.RequestKafkaMessage;
import com.arcariel.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody RequestKafkaMessage request) {
        this.kafkaProducer.sendMessage(request.getMessage());
        return ResponseEntity.ok("Message sent...");
    }

}
