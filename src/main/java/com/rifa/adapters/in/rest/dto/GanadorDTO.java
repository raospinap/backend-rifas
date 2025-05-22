package com.rifa.adapters.in.rest.dto;

public class GanadorDTO {
    private String correo;
    private Long totalPremios;

    public GanadorDTO(String correo, Long totalPremios) {
        this.correo = correo;
        this.totalPremios = totalPremios;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getTotalPremios() {
        return totalPremios;
    }

    public void setTotalPremios(Long totalPremios) {
        this.totalPremios = totalPremios;
    }
}
