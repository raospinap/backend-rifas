package com.rifa.adapters.out.messaging.dto;

import java.time.LocalDateTime;

public class ParticipacionMessage {

    private Long idUsuario;
    private Long idRifa;
    private LocalDateTime fechaHora;

    public ParticipacionMessage() {}

    public ParticipacionMessage(Long idUsuario, Long idRifa, LocalDateTime fechaHora) {
        this.idUsuario = idUsuario;
        this.idRifa = idRifa;
        this.fechaHora = fechaHora;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRifa() {
        return idRifa;
    }

    public void setIdRifa(Long idRifa) {
        this.idRifa = idRifa;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
