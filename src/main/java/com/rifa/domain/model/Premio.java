package com.rifa.domain.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "premios")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    private Usuario ganador;
    
    @ManyToOne
    @JoinColumn(name = "rifa_id")
    @JsonIgnore
    private Rifa rifa;

    public Premio() {}

    public Premio(String nombre, String icono) {
        this.nombre = nombre;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rifa getRifa() {
        return rifa;
    }

    public void setRifa(Rifa rifa) {
        this.rifa = rifa;
    }
    
    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

}
