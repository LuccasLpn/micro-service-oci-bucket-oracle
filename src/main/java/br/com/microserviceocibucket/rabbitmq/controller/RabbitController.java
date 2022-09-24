package br.com.microserviceocibucket.rabbitmq.controller;

import br.com.microserviceocibucket.rabbitmq.dto.ConsumerQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/producer")
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queueName}")
    private String QUEUE_NAME;

    @PostMapping(path = "/save")
    public ResponseEntity<Void> sendProducer(@RequestBody ConsumerQueue consumerQueue){
        rabbitTemplate.convertAndSend(QUEUE_NAME, consumerQueue);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
