package com.rifa.application;

import com.rifa.adapters.out.messaging.EventoProducer;
import com.rifa.adapters.out.messaging.dto.EventoMessage;
import com.rifa.adapters.out.persistence.ParticipacionJpaRepository;
import com.rifa.domain.exceptions.ParticipacionDuplicadaException;
import com.rifa.adapters.out.persistence.RifaJpaRepository;
import com.rifa.adapters.out.persistence.UsuarioJpaRepository;
import com.rifa.domain.model.Participacion;
import com.rifa.domain.model.Rifa;
import com.rifa.domain.model.Usuario;
import com.rifa.domain.ports.in.ParticipacionService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParticipacionServiceImpl implements ParticipacionService{

	private final ParticipacionJpaRepository participacionRepo;
	private final RifaJpaRepository rifaRepo;
	private final EventoProducer eventoProducer;
	private final UsuarioJpaRepository usuarioRepo;
	
	public ParticipacionServiceImpl(
	    ParticipacionJpaRepository participacionRepo,
	    RifaJpaRepository rifaRepo,
	    EventoProducer eventoProducer,
	    UsuarioJpaRepository usuarioRepo
	) {
	    this.participacionRepo = participacionRepo;
	    this.rifaRepo = rifaRepo;
	    this.eventoProducer = eventoProducer;
	    this.usuarioRepo = usuarioRepo;
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
        
        Usuario usuario = usuarioRepo.findById(idUsuario)
        	    .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        	String correo = usuario.getCorreo();
        	String tema = rifa.getTema();

        
        	eventoProducer.enviarEvento(new EventoMessage(
        		    "PARTICIPACION",
        		    "👤 " + correo + " participó en la rifa \"" + tema + "\"",
        		    fechaHora
        	));


    }

    
}
