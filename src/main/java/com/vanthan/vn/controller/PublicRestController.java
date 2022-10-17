package com.vanthan.vn.controller;

import com.vanthan.vn.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicRestController extends BaseController {

    public static final String TOPIC = "NewTopic";
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("spring-kafka/{message}")
    public String publicMessage(@PathVariable String message) {
//        kafkaTemplate.send(TOPIC, message);
        return "public message success";
    }

    @PostMapping("spring-kafka/data")
    public String publicMessage(@RequestBody User user) {
        kafkaTemplate.send(TOPIC, user);
        return "public message success";
    }
}
