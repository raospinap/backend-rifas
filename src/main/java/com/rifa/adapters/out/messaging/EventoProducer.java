package com.rifa.adapters.out.messaging;

import com.rifa.adapters.out.messaging.dto.EventoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventoProducer {

    private final RabbitTemplate rabbitTemplate;

    public EventoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEvento(EventoMessage evento) {
        rabbitTemplate.convertAndSend("eventos.rifa", evento);
    }
}
