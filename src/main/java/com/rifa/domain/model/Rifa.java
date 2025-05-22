package com.rifa.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;


@Entity
@Table(name = "rifas")
public class Rifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tema;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFin;

    @Column(nullable = false)
    private Integer duracionMinutos;

    @Enumerated(EnumType.STRING)
    private EstadoRifa estado;

    @Column(nullable = false)
    private Integer numGanadores;
    
    @OneToMany(mappedBy = "rifa", cascade = CascadeType.ALL)
    private List<Premio> premios;


    public Rifa() {}

    public Rifa(Long id, String tema, LocalDateTime fechaInicio, LocalDateTime fechaFin,
                Integer duracionMinutos, EstadoRifa estado, Integer numGanadores) {
        this.id = id;
        this.tema = tema;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionMinutos = duracionMinutos;
        this.estado = estado;
        this.numGanadores = numGanadores;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public Rifa(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public EstadoRifa getEstado() {
        return estado;
    }

    public void setEstado(EstadoRifa estado) {
        this.estado = estado;
    }

    public Integer getNumGanadores() {
        return numGanadores;
    }

    public void setNumGanadores(Integer numGanadores) {
        this.numGanadores = numGanadores;
    }

    public enum EstadoRifa {
        ACTIVA,
        SORTEADA, 
        FINALIZADA
    }
    
    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> premios) {
        this.premios = premios;
    }

}
