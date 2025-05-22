package com.rifa.adapters.out.persistence;

import com.rifa.adapters.in.rest.dto.PremioRecienteDTO;
import com.rifa.adapters.in.rest.dto.PremioUsuarioDTO;
import com.rifa.domain.model.Usuario;
import com.rifa.domain.ports.out.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;
    private final PremioJpaRepository premioJpaRepository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository jpaRepository, PremioJpaRepository premioJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.premioJpaRepository = premioJpaRepository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return jpaRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return jpaRepository.findByCorreo(correo);
    }
    
    @Override
    public List<Object[]> obtenerRankingUsuarios(int limite) {
        return jpaRepository.obtenerRankingUsuarios(PageRequest.of(0, limite));
    }
    
    @Override
    public List<Object[]> obtenerRankingPorRifa(Long rifaId) {
        return premioJpaRepository.obtenerGanadoresPorRifa(rifaId);
    }
    
    @Override
    public List<PremioUsuarioDTO> obtenerPremiosPorUsuario(Long usuarioId) {
        return premioJpaRepository.obtenerPremiosPorUsuario(usuarioId);
    }

    @Override
    public List<PremioRecienteDTO> obtenerUltimosPremiosOtorgados(int cantidad) {
        Pageable limit = PageRequest.of(0, cantidad);
        List<Object[]> resultados = premioJpaRepository.obtenerUltimosPremios(limit);

        return resultados.stream()
                .map(obj -> new PremioRecienteDTO(
                        (String) obj[0],      // correo
                        (String) obj[1],      // nombre del premio
                        (LocalDateTime) obj[2]    // fecha de la rifa
                ))
                .toList();
    }

    
}
