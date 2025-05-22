package com.rifa.domain.ports.in;

public interface VerificacionCorreoService {

    void enviarCodigoOTP(String correo);

    boolean verificarCodigo(String correo, String codigo);
}
