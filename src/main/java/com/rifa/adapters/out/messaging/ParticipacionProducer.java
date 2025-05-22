package com.rifa.adapters.out.messaging;

import com.rifa.adapters.out.messaging.dto.ParticipacionMessage;
import com.rifa.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ParticipacionProducer {

    private final RabbitTemplate rabbitTemplate;

    public ParticipacionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarParticipacion(ParticipacionMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.PARTICIPACION_QUEUE, message);
    }
}
