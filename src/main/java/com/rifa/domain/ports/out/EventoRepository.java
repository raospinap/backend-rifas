package com.rifa.domain.ports.out;

import com.rifa.domain.model.Evento;
import java.util.List;

public interface EventoRepository {
    void guardarEvento(Evento evento);
    List<Evento> obtenerUltimos(int cantidad);
}
