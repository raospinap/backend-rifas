package com.rifa.adapters.in.messaging;

import com.rifa.adapters.out.messaging.dto.ParticipacionMessage;
import com.rifa.application.ParticipacionService;
import com.rifa.config.RabbitMQConfig;
import com.rifa.domain.exceptions.ParticipacionDuplicadaException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ParticipacionConsumer {

    private final ParticipacionService participacionService;

    public ParticipacionConsumer(ParticipacionService participacionService) {
        this.participacionService = participacionService;
    }

    @RabbitListener(queues = RabbitMQConfig.PARTICIPACION_QUEUE)
    public void recibirParticipacion(ParticipacionMessage mensaje) {
        System.out.println("📥 Mensaje recibido: " + mensaje.getIdUsuario() + " participa en rifa " + mensaje.getIdRifa());

        try {
            participacionService.registrarParticipacion(
                mensaje.getIdUsuario(),
                mensaje.getIdRifa(),
                mensaje.getFechaHora()
            );
        } catch (ParticipacionDuplicadaException ex) {
            System.out.println("⚠️ " + ex.getMessage());
        }catch (RuntimeException ex) {
            System.out.println("❌ Error al registrar participación: " + ex.getMessage());
        
        }

    }
}
