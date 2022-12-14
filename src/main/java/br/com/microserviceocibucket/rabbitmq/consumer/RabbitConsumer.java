package br.com.microserviceocibucket.rabbitmq.consumer;

import br.com.microserviceocibucket.oracle.config.OracleConfig;
import br.com.microserviceocibucket.oracle.dto.UploadOracle;
import br.com.microserviceocibucket.rabbitmq.dto.ConsumerQueue;
import br.com.microserviceocibucket.util.Decode;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class RabbitConsumer {

    private final OracleConfig oracleConfig;


    @RabbitListener(queues = "oci-upload")
    public void consumer(ConsumerQueue consumerQueue) throws IOException {
        InputStream inputStream = new Decode().decodeString(consumerQueue.getMidia());
        UploadOracle uploadOracle = UploadOracle.
                builder()
                .midia(inputStream)
                .bucketName(consumerQueue.getBucketName())
                .nameSpace(consumerQueue.getNameSpace())
                .fileName(consumerQueue.getFileName())
                .build();
            oracleConfig.uploadBucket(uploadOracle);
    }
}

