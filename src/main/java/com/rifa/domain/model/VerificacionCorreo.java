package com.rifa.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verificaciones_correo")
public class VerificacionCorreo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String otp;

    private LocalDateTime fechaEnvio;
    private LocalDateTime expiraEn;
    private boolean usado;

    public VerificacionCorreo() {}

    public VerificacionCorreo(Long id, String correo, String otp, LocalDateTime fechaEnvio, LocalDateTime expiraEn, boolean usado) {
        this.id = id;
        this.correo = correo;
        this.otp = otp;
        this.fechaEnvio = fechaEnvio;
        this.expiraEn = expiraEn;
        this.usado = usado;
 }
    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDateTime getExpiraEn() {
        return expiraEn;
    }

    public void setExpiraEn(LocalDateTime expiraEn) {
        this.expiraEn = expiraEn;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}
