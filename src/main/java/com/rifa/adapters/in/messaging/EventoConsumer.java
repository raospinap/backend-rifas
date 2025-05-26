package com.rifa.adapters.in.messaging;

import com.rifa.adapters.out.messaging.dto.EventoMessage;
import com.rifa.domain.model.Evento;
import com.rifa.domain.ports.out.EventoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventoConsumer {

    private final EventoRepository eventoRepository;

    public EventoConsumer(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @RabbitListener(queues = "eventos.rifa")
    public void recibirEvento(EventoMessage mensaje) {
        Evento evento = new Evento();
        evento.setTipo(mensaje.getTipo());
        evento.setMensaje(mensaje.getMensaje());
        evento.setFecha(mensaje.getFecha());

        eventoRepository.guardarEvento(evento);
    }
}
