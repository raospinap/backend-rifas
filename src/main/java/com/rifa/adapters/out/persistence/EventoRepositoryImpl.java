package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.Evento;
import com.rifa.domain.ports.out.EventoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventoRepositoryImpl implements EventoRepository {

    private final EventoJpaRepository eventoJpaRepository;

    public EventoRepositoryImpl(EventoJpaRepository eventoJpaRepository) {
        this.eventoJpaRepository = eventoJpaRepository;
    }

    @Override
    public void guardarEvento(Evento evento) {
        eventoJpaRepository.save(evento);
    }

    @Override
    public List<Evento> obtenerUltimos(int cantidad) {
        return eventoJpaRepository.findTop10ByOrderByFechaDesc();
    }
}
