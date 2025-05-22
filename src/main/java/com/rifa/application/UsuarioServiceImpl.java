package com.rifa.application;

import com.rifa.adapters.in.rest.dto.PremioRecienteDTO;
import com.rifa.adapters.in.rest.dto.PremioUsuarioDTO;
import com.rifa.domain.model.Usuario;
import com.rifa.domain.model.Usuario.EstadoUsuario;
import com.rifa.domain.ports.in.UsuarioService;
import com.rifa.domain.ports.out.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.rifa.adapters.out.persistence.PremioJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final PremioJpaRepository premioJpaRepository;
    private final AtomicLong idGenerator = new AtomicLong(1); // Solo temporal para asignar IDs en memoria
    
    public UsuarioServiceImpl(UsuarioRepository usuarioRepo,PremioJpaRepository premioJpaRepository) {
        this.usuarioRepo = usuarioRepo;
        this.premioJpaRepository = premioJpaRepository;
    }
    
    @Override
    public Usuario crearUsuarioSiNoExiste(String correo, String nombre) {
        Optional<Usuario> existente = usuarioRepo.buscarPorCorreo(correo);

        if (existente.isPresent()) {
            return existente.get(); 
        }

        Usuario nuevo = new Usuario();
        nuevo.setId(idGenerator.getAndIncrement());
        nuevo.setCorreo(correo);
        nuevo.setNombre(nombre);
        nuevo.setEstado(EstadoUsuario.ACTIVO);

        return usuarioRepo.guardar(nuevo);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return usuarioRepo.buscarPorCorreo(correo);
    }
    
    @Override
    public List<PremioUsuarioDTO> obtenerPremiosPorUsuario(Long idUsuario) {
        return premioJpaRepository.obtenerPremiosPorUsuario(idUsuario);
    }

    @Override
    public List<PremioRecienteDTO> obtenerUltimosPremiosOtorgados(int cantidad) {
        return usuarioRepo.obtenerUltimosPremiosOtorgados(cantidad);
    }

    
}
