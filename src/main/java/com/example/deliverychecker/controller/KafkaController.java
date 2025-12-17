package com.example.deliverychecker.controller;

import com.example.deliverychecker.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService producer;

    public KafkaController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(
            @RequestParam(value = "msg", required = false) String msg,
            @RequestBody(required = false) Map<String, String> body
    ) {
        // Accept JSON: { "msg": "hello" } or { "message": "hello" }
        if (msg == null && body != null) {
            msg = body.getOrDefault("msg", body.get("message"));
        }

        if (msg == null || msg.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Missing message. Use /kafka/publish?msg=Hello OR send JSON {\"msg\":\"Hello\"}");
        }

        producer.sendMessage(msg);
        return ResponseEntity.ok("Message Sent: " + msg);
    }
}
