package dz.kyrios.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ProducerController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/api/v1/producer")
    public ResponseEntity<Object> getAllFilter() {
        try {
            String payload = "Hello World";
            kafkaTemplate.send("notificationTopic", payload);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
