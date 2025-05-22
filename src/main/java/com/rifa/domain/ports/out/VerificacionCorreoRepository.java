package com.rifa.domain.ports.out;

import com.rifa.domain.model.VerificacionCorreo;
import java.util.List;
import java.util.Optional;

public interface VerificacionCorreoRepository {

    void guardar(VerificacionCorreo verificacion);

    Optional<VerificacionCorreo> buscarPorCorreoYCodigo(String correo, String codigo);
    
    List<VerificacionCorreo> buscarTodosActivosPorCorreo(String correo);
}
