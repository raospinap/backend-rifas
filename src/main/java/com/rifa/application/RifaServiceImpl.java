package com.rifa.application;

import com.rifa.adapters.out.external.OpenAIService;
import com.rifa.adapters.out.persistence.ParticipacionJpaRepository;
import com.rifa.adapters.out.persistence.PremioJpaRepository;
import com.rifa.adapters.out.persistence.RifaJpaRepository;
import com.rifa.domain.model.Participacion;
import com.rifa.domain.model.Usuario;
import com.rifa.domain.model.Premio;
import com.rifa.domain.model.Rifa;
import com.rifa.domain.model.Rifa.EstadoRifa;
import com.rifa.domain.ports.in.RifaService;
import com.rifa.domain.ports.out.RifaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Service
public class RifaServiceImpl implements RifaService {

    private final RifaRepository rifaRepository;
    private final OpenAIService openAIService;
    private final ParticipacionJpaRepository participacionJpaRepository;
    private final PremioJpaRepository premioJpaRepository;
    private final RifaJpaRepository rifaJpaRepository;


    public RifaServiceImpl(
    	    RifaRepository rifaRepository,
    	    OpenAIService openAIService,
    	    ParticipacionJpaRepository participacionJpaRepository,
    	    PremioJpaRepository premioJpaRepository,
    	    RifaJpaRepository rifaJpaRepository
    	) {
    	    this.rifaRepository = rifaRepository;
    	    this.openAIService = openAIService;
    	    this.participacionJpaRepository = participacionJpaRepository;
    	    this.premioJpaRepository = premioJpaRepository;
    	    this.rifaJpaRepository = rifaJpaRepository;
    	}

    
    
    @Override
    public Rifa crearRifa(String tema, Integer duracionMinutos, Integer numGanadores) {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fin = inicio.plusMinutes(duracionMinutos);

        Rifa nueva = new Rifa();
        nueva.setTema(tema);
        nueva.setFechaInicio(inicio);
        nueva.setFechaFin(fin);
        nueva.setDuracionMinutos(duracionMinutos);
        nueva.setEstado(EstadoRifa.ACTIVA);
        nueva.setNumGanadores(numGanadores);

        // Generar premios usando OpenAI
        List<Premio> premiosGenerados = openAIService.generarPremios(tema, numGanadores);
        
       
        // Asignar rifa a cada premio generado
        for (Premio premio : premiosGenerados) {
            premio.setRifa(nueva);
        }
        
        // Asignar la lista de premios a la rifa
        nueva.setPremios(premiosGenerados);
        
        return rifaRepository.guardar(nueva);
    }

    @Override
    public List<Rifa> listarRifasActivas() {
        return rifaRepository.listarRifasActivas();
    }

    @Override
    public Optional<Rifa> buscarPorId(Long id) {
        return rifaRepository.buscarPorId(id);
    }
    
    @Override
    public List<Rifa> listarPorEstado(Rifa.EstadoRifa estado) {
        return rifaRepository.findByEstado(estado);
    }

    @Override
    public List<Rifa> listarRifasActivasPorUsuario(Long idUsuario) {
        List<Long> idsRifas = participacionJpaRepository.findIdsRifasPorUsuario(idUsuario);
        List<Rifa> todas = rifaRepository.findByEstado(Rifa.EstadoRifa.ACTIVA);

        return todas.stream()
            .filter(r -> idsRifas.contains(r.getId()))
            .toList();
    }

    @Override
    public List<Rifa> listarRifasDisponiblesParaUsuario(Long idUsuario) {
        List<Long> idsParticipadas = participacionJpaRepository.findIdsRifasPorUsuario(idUsuario);
        List<Rifa> activas = rifaRepository.findByEstado(Rifa.EstadoRifa.ACTIVA);

        return activas.stream()
            .filter(rifa -> !idsParticipadas.contains(rifa.getId()))
            .toList();
    }
    
    @Override
    public Rifa terminarRifa(Long idRifa, boolean sortear) {
        Optional<Rifa> opt = rifaRepository.buscarPorId(idRifa);

        if (opt.isEmpty()) {
            throw new RuntimeException("Rifa no encontrada con ID: " + idRifa);
        }

        Rifa rifa = opt.get();

        if (rifa.getEstado() != EstadoRifa.ACTIVA) {
            throw new RuntimeException("La rifa no está activa y no puede ser finalizada.");
        }

        if (sortear) {
            sortearRifa(rifa);
        } else {
            rifa.setEstado(EstadoRifa.FINALIZADA);
        }

        return rifaRepository.guardar(rifa);
    }

    public void sortearRifasVencidas() {
        List<Rifa> rifasVencidas = rifaJpaRepository
                .findConPremiosByEstadoAndFechaFinBefore(EstadoRifa.ACTIVA, LocalDateTime.now());

        for (Rifa rifa : rifasVencidas) {
            sortearRifa(rifa);
            rifaRepository.guardar(rifa);
        }
    }

    private void sortearRifa(Rifa rifa) {
        List<Participacion> participaciones = participacionJpaRepository.findByRifa(rifa);

        if (participaciones.size() < rifa.getNumGanadores()) {
            System.out.println("⚠️ No hay suficientes participantes para la rifa " + rifa.getId());
            return;
        }

        Collections.shuffle(participaciones);
        List<Participacion> ganadores = participaciones.subList(0, rifa.getNumGanadores());
        List<Premio> premios = rifa.getPremios();

        for (int i = 0; i < ganadores.size(); i++) {
            Premio premio = premios.get(i);
            Participacion ganador = ganadores.get(i);

            premio.setGanador(new Usuario(ganador.getIdUsuario()));
            premioJpaRepository.save(premio);

            System.out.println("🎉 Ganador: Usuario " + ganador.getIdUsuario() + " - Premio: " + premio.getNombre());
        }

        rifa.setEstado(EstadoRifa.SORTEADA);
    }    
       
}
