package com.rifa.adapters.out.messaging.dto;

import java.time.LocalDateTime;

public class EventoMessage {
    private String tipo;         // e.g. PARTICIPACION, SORTEO, RIFA_CREADA
    private String mensaje;      
    private LocalDateTime fecha; 

    public EventoMessage() {}

    public EventoMessage(String tipo, String mensaje, LocalDateTime fecha) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
