package br.com.rabbitMQ.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitServices {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(final String queueName, final Object message) {
        this.rabbitTemplate.convertAndSend(queueName, message);
    }
}
