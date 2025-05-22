package com.rifa.adapters.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearRifaDTO {

    @NotBlank(message = "El tema no puede estar vacío.")
    private String tema;

    @NotNull(message = "La duración debe especificarse.")
    @Min(value = 1, message = "La duración mínima es 1 minuto.")
    private Integer duracionMinutos;

    @NotNull(message = "El número de ganadores debe especificarse.")
    @Min(value = 1, message = "Debe haber al menos 1 ganador.")
    private Integer numGanadores;

    // Getters y Setters

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Integer getNumGanadores() {
        return numGanadores;
    }

    public void setNumGanadores(Integer numGanadores) {
        this.numGanadores = numGanadores;
    }
}
