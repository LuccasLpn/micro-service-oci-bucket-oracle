package br.com.microserviceocibucket.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerQueue implements Serializable {
    private String midia;
    private String nameSpace;
    private String bucketName;
    private String fileName;
}
