package com.rifa.domain.ports.in;

import com.rifa.domain.model.Rifa;
import java.util.List;
import java.util.Optional;

public interface RifaService {

    Rifa crearRifa(String tema, Integer duracionMinutos, Integer numGanadores);

    List<Rifa> listarRifasActivas();

    Optional<Rifa> buscarPorId(Long id);

    List<Rifa> listarPorEstado(Rifa.EstadoRifa estado);
    
    Rifa terminarRifa(Long idRifa, boolean sortear);
    
    List<Rifa> listarRifasActivasPorUsuario(Long idUsuario);

    List<Rifa> listarRifasDisponiblesParaUsuario(Long idUsuario);

}
