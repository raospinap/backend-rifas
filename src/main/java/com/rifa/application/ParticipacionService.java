package com.rifa.application;

import com.rifa.adapters.out.persistence.ParticipacionJpaRepository;
import com.rifa.domain.exceptions.ParticipacionDuplicadaException;
import com.rifa.adapters.out.persistence.RifaJpaRepository;
import com.rifa.domain.model.Participacion;
import com.rifa.domain.model.Rifa;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParticipacionService {

    private final ParticipacionJpaRepository participacionRepo;
    private final RifaJpaRepository rifaRepo; 
    public ParticipacionService(ParticipacionJpaRepository participacionRepo, RifaJpaRepository rifaRepo) {
        this.participacionRepo = participacionRepo;
        this.rifaRepo = rifaRepo;
    }

    public void registrarParticipacion(Long idUsuario, Long idRifa, LocalDateTime fechaHora) {

        Rifa rifa = rifaRepo.findById(idRifa)
            .orElseThrow(() -> new RuntimeException("Rifa no encontrada."));

        if (rifa.getEstado() != Rifa.EstadoRifa.ACTIVA) {
            throw new RuntimeException("❌ No se puede participar en una rifa que no está activa.");
        }

        if (participacionRepo.existsByIdUsuarioAndRifa_Id(idUsuario, idRifa)) {
            throw new ParticipacionDuplicadaException(idUsuario, idRifa);
        }

        Participacion participacion = new Participacion();
        participacion.setIdUsuario(idUsuario); 
        participacion.setRifa(rifa);
        participacion.setFechaHora(fechaHora);

        participacionRepo.save(participacion);
    }

    
}
