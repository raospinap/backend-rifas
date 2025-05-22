package com.rifa.domain.ports.out;

import com.rifa.adapters.in.rest.dto.PremioRecienteDTO;
import com.rifa.adapters.in.rest.dto.PremioUsuarioDTO;
import com.rifa.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorCorreo(String correo);
    
    List<Object[]> obtenerRankingUsuarios(int limite);
    
    List<Object[]> obtenerRankingPorRifa(Long rifaId);

    List<PremioUsuarioDTO> obtenerPremiosPorUsuario(Long usuarioId);

    List<PremioRecienteDTO> obtenerUltimosPremiosOtorgados(int cantidad);


}
