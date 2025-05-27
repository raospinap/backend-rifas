package com.rifa.adapters.out.messaging.dto;

public class OtpMessage {
    private String correo;
    private String codigo;

    public OtpMessage() {}

    public OtpMessage(String correo, String codigo) {
        this.correo = correo;
        this.codigo = codigo;
    }

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
