package com.rifa.adapters.out.persistence;

import com.rifa.domain.model.Rifa;
import com.rifa.domain.ports.out.RifaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RifaRepositoryImpl implements RifaRepository {

    private final RifaJpaRepository jpaRepository;

    public RifaRepositoryImpl(RifaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Rifa guardar(Rifa rifa) {
        return jpaRepository.save(rifa);
    }

    @Override
    public List<Rifa> listarRifasActivas() {
        return jpaRepository.findByEstado("ACTIVA");
    }

    @Override
    public Optional<Rifa> buscarPorId(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Rifa> findByEstado(Rifa.EstadoRifa estado) {
        return jpaRepository.findByEstado(estado);
    }

}
