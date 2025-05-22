package com.rifa.adapters.in.rest.dto;

public class GanadorPremioDTO {
    private String correo;
    private String premio;

    public GanadorPremioDTO(String correo, String premio) {
        this.correo = correo;
        this.premio = premio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }
}
