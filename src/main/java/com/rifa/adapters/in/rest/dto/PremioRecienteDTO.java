package com.rifa.adapters.in.rest.dto;

import java.time.LocalDateTime;

public class PremioRecienteDTO {
    private String correo;
    private String premio;
    private LocalDateTime fechaRifa;

    public PremioRecienteDTO(String correo, String premio, LocalDateTime fechaRifa) {
        this.correo = correo;
        this.premio = premio;
        this.fechaRifa = fechaRifa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public LocalDateTime getFechaRifa() {
        return fechaRifa;
    }

    public void setFechaRifa(LocalDateTime fechaRifa) {
        this.fechaRifa = fechaRifa;
    }
}
