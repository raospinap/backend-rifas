package com.rifa.domain.ports.in;

import com.rifa.adapters.in.rest.dto.PremioRecienteDTO;
import com.rifa.adapters.in.rest.dto.PremioUsuarioDTO;
import com.rifa.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario crearUsuarioSiNoExiste(String correo, String nombre);

    Optional<Usuario> buscarUsuarioPorCorreo(String correo);
    
    List<PremioUsuarioDTO> obtenerPremiosPorUsuario(Long usuarioId);

    List<PremioRecienteDTO> obtenerUltimosPremiosOtorgados(int cantidad);


}
