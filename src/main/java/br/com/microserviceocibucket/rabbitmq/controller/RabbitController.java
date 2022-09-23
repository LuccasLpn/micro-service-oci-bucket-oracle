package br.com.microserviceocibucket.rabbitmq.controller;

import br.com.microserviceocibucket.rabbitmq.dto.UploadBucket;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/save")
    public ResponseEntity<Void> sendProducer(@RequestBody UploadBucket uploadBucket){
        rabbitTemplate.convertAndSend("oci-upload", uploadBucket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
