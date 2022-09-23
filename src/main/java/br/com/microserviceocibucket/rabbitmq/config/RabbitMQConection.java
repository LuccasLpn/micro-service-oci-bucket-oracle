package br.com.microserviceocibucket.rabbitmq.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";

    @Value("${spring.rabbitmq.queueName}")
    private String QUEUE_NAME;

    private final AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(QUEUE_NAME);
        DirectExchange troca = this.trocaDireta();
        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacaoEstoque);
    }

}
