package com.rabbitmq.springboot_rabbitmq.controller;

import com.rabbitmq.springboot_rabbitmq.dto.User;
import com.rabbitmq.springboot_rabbitmq.producer.EventProducer;
import com.rabbitmq.springboot_rabbitmq.producer.JsonEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class EventController {

    private EventProducer eventProducer;

    private JsonEventProducer jsonEventProducer;

    public EventController(EventProducer eventProducer, JsonEventProducer jsonEventProducer) {
        this.eventProducer = eventProducer;
        this.jsonEventProducer = jsonEventProducer;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEvent(@RequestParam("message") String message){

        eventProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent !");

    }

    @PostMapping("/send-json")
    public ResponseEntity<String> sendJsonEvent(@RequestBody User user){

        jsonEventProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message Sent");
    }

}
