package com.rifa.adapters.in.rest;

import com.rifa.domain.ports.out.EventoRepository;
import com.rifa.domain.model.Evento;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/ultimos")
    public List<Evento> obtenerUltimosEventos(@RequestParam(defaultValue = "10") int cantidad) {
        return eventoRepository.obtenerUltimos(cantidad);
    }
}
