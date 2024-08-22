package com.rabbitmq.springboot_rabbitmq.controller;

import com.rabbitmq.springboot_rabbitmq.producer.EventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class EventController {

    private EventProducer eventProducer;

    public EventController(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEvent(@RequestParam("message") String message){

        eventProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent !");

    }

}
