package com.rifa.adapters.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VerificacionDTO {

    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "El formato del correo no es válido.")
    private String correo;

    @NotBlank(message = "El código no puede estar vacío.")
    private String codigo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
