package com.rifa.domain.ports.out;

import com.rifa.domain.model.Rifa;
import java.util.List;
import java.util.Optional;

public interface RifaRepository {

    Rifa guardar(Rifa rifa);

    List<Rifa> listarRifasActivas();

    Optional<Rifa> buscarPorId(Long id);
    
    List<Rifa> findByEstado(Rifa.EstadoRifa estado);

}
