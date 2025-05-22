package com.rifa.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participaciones")
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "rifa_id", nullable = false)
    private Rifa rifa;

    private LocalDateTime fechaHora;

    public Participacion() {}

    public Participacion(Long idUsuario, Rifa rifa, LocalDateTime fechaHora) {
        this.idUsuario = idUsuario;
        this.rifa = rifa;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rifa getRifa() {
        return rifa;
    }

    public void setRifa(Rifa rifa) {
        this.rifa = rifa;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
