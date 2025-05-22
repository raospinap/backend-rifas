package com.rifa.adapters.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CorreoDTO {

    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "El formato del correo no es válido.")
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
