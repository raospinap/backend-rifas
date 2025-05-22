package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.VerificacionCorreo;
import com.rifa.domain.ports.out.VerificacionCorreoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VerificacionCorreoRepositoryImpl implements VerificacionCorreoRepository {

    private final VerificacionCorreoJpaRepository jpaRepository;

    public VerificacionCorreoRepositoryImpl(VerificacionCorreoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(VerificacionCorreo verificacion) {
        jpaRepository.save(verificacion);
    }

    @Override
    public Optional<VerificacionCorreo> buscarPorCorreoYCodigo(String correo, String codigo) {
        return jpaRepository.findByCorreoAndOtp(correo, codigo);
    }
    
    @Override
    public List<VerificacionCorreo> buscarTodosActivosPorCorreo(String correo) {
        return jpaRepository.findByCorreoAndUsadoFalse(correo);
    }

}
