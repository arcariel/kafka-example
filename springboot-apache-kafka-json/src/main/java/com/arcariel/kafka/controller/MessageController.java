package com.arcariel.kafka.controller;

import com.arcariel.kafka.model.User;
import com.arcariel.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/publish-json")
    public ResponseEntity<String> publish(@RequestBody User user) {
        this.kafkaProducer.sendMessage(user);
        return ResponseEntity.ok("JSON message sent...");
    }

}
