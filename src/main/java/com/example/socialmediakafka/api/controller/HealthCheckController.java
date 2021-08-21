package com.example.socialmediakafka.api.controller;

import com.example.socialmediakafka.commons.KafkaUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.*;


@RequestMapping("")
@RestController
public class HealthCheckController {

    @GetMapping
    @ResponseBody
    public void getHealthCheck() {
        KafkaUtils.sendMessage("health","id","true");
    }
}
