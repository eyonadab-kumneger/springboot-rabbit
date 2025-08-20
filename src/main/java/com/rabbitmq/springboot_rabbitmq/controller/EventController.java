package com.rabbitmq.springboot_rabbitmq.controller;

import com.rabbitmq.springboot_rabbitmq.dto.User;
import com.rabbitmq.springboot_rabbitmq.producer.StringEventProducer;
import com.rabbitmq.springboot_rabbitmq.producer.JsonEventProducer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
@NoArgsConstructor
public class EventController {

    private StringEventProducer eventProducer;
    private JsonEventProducer jsonEventProducer;

    @PostMapping("/send")
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
