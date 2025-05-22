package com.rifa.adapters.in.rest.dto;

import java.time.LocalDateTime;

public class PremioUsuarioDTO {
    private String premio;
    private String temaRifa;
    private LocalDateTime fechaFin;

    public PremioUsuarioDTO(String premio, String temaRifa, LocalDateTime fechaFin) {
        this.premio = premio;
        this.temaRifa = temaRifa;
        this.fechaFin = fechaFin;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public String getTemaRifa() {
        return temaRifa;
    }

    public void setTemaRifa(String temaRifa) {
        this.temaRifa = temaRifa;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

}
