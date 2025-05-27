package com.rifa.adapters.out.messaging;

import com.rifa.adapters.out.messaging.dto.OtpMessage;
import com.rifa.config.RabbitMQConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OtpProducer {

    private final RabbitTemplate rabbitTemplate;

    public OtpProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarOtp(OtpMessage otp) {
    	rabbitTemplate.convertAndSend(RabbitMQConfig.OTP_QUEUE, otp);

    }
}
