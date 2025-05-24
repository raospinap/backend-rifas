package com.rifa.adapters.in.rest.dto;

import com.rifa.domain.model.Rifa;
import com.rifa.domain.model.Premio;
import java.time.LocalDateTime;
import java.util.List;

public class RifaConParticipantesDTO {
    public Long id;
    public String tema;
    public LocalDateTime fechaInicio;
    public LocalDateTime fechaFin;
    public Integer duracionMinutos;
    public String estado;
    public Integer numGanadores;
    public List<Premio> premios;
    public Long numeroParticipantes;

    public RifaConParticipantesDTO(Rifa rifa, Long numeroParticipantes) {
        this.id = rifa.getId();
        this.tema = rifa.getTema();
        this.fechaInicio = rifa.getFechaInicio();
        this.fechaFin = rifa.getFechaFin();
        this.duracionMinutos = rifa.getDuracionMinutos();
        this.estado = rifa.getEstado().name();
        this.numGanadores = rifa.getNumGanadores();
        this.premios = rifa.getPremios();
        this.numeroParticipantes = numeroParticipantes;
    }
}
