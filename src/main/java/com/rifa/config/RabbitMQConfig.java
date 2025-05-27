package com.rifa.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class RabbitMQConfig {

	public static final String PARTICIPACION_QUEUE = "participaciones";
	public static final String OTP_QUEUE = "otp.envio";
	public static final String EVENTOS_QUEUE = "eventos.rifa";



	@Bean
	public Queue participacionQueue() {
	    return new Queue(PARTICIPACION_QUEUE, true); 
	}
	
	@Bean
	public Queue otpQueue() {
	    return new Queue(OTP_QUEUE, true);
	}

	@Bean
	public Queue eventosRifaQueue() {
	    return new Queue(EVENTOS_QUEUE, true);
	}
	
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
        
}
