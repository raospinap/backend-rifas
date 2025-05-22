package com.rifa.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String correo;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.USUARIO; 
    
    public Usuario() {}

    public Usuario(Long id, String correo, String nombre, EstadoUsuario estado) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.estado = estado;
        this.rol= Rol.USUARIO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario(Long id) {
        this.id = id;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public enum EstadoUsuario {
        ACTIVO, INACTIVO
    }

    public enum Rol {
        USUARIO,
        ADMIN
    }
}
