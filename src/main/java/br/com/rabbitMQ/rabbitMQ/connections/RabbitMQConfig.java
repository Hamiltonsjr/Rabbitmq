package br.com.rabbitMQ.rabbitMQ.connections;

import br.com.rabbitMQ.rabbitMQ.contants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RabbitMQConfig {

    private static final String EXCHANGE = "amq.direct";

    private final AmqpAdmin adim;

    public RabbitMQConfig(AmqpAdmin adim) {
        this.adim = adim;
    }
    // mensagens serão amarzenadas nessa fila
    private Queue queue(final String nameQueue) {
        return new Queue(nameQueue, true, false, false);
    }
    // exchange cria a troca entre as filas
    private DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    private Binding relations(final Queue queue, final DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange().getName(), queue.getName(), null);
    }

    @PostConstruct
    private void createQueue() {
        final Queue queueStock = this.queue(RabbitMQConstants.QUEUE_STOCK);
        final Queue queuePrice = this.queue(RabbitMQConstants.QUEUE_PRICE);

        final DirectExchange directExchange = this.exchange();

        final Binding relationsStock = this.relations(queueStock, directExchange);
        final Binding relationsPrice = this.relations(queuePrice, directExchange);

        // criando as filas no rabbitmq
        this.adim.declareQueue(queueStock);
        this.adim.declareQueue(queuePrice);

        this.adim.declareExchange(directExchange);

        this.adim.declareBinding(relationsStock);
        this.adim.declareBinding(relationsPrice);
    }

}
