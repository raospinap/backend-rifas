package com.rifa.adapters.in.rest;

import com.rifa.adapters.in.rest.dto.GanadorDTO;
import com.rifa.adapters.in.rest.dto.GanadorPremioDTO;
import com.rifa.adapters.in.rest.dto.PremioRecienteDTO;
import com.rifa.adapters.in.rest.dto.PremioUsuarioDTO;
import com.rifa.domain.ports.in.RankingService;
import com.rifa.domain.ports.in.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;
    private final UsuarioService usuarioService;

    public RankingController(RankingService rankingService,UsuarioService usuarioService) {
        this.rankingService = rankingService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/ganadores")
    public List<GanadorDTO> obtenerTopGanadores() {
        return rankingService.obtenerTopUsuariosConMasPremios(10)
                .stream()
                .map(obj -> new GanadorDTO((String) obj[0], (Long) obj[1]))
                .toList();
    }
    
    @GetMapping("/ganadores/rifa/{id}")
    public List<GanadorPremioDTO> obtenerGanadoresPorRifa(@PathVariable("id") Long rifaId) {
        return rankingService.obtenerRankingPorRifa(rifaId).stream()
                .map(obj -> new GanadorPremioDTO((String) obj[0], (String) obj[1]))
                .toList();
    }

    @GetMapping("/usuarios/{id}/premios")
    public List<PremioUsuarioDTO> obtenerPremiosDeUsuario(@PathVariable("id") Long usuarioId) {
        return usuarioService.obtenerPremiosPorUsuario(usuarioId);
    }

    @GetMapping("/ganadores/ultimos")
    public List<PremioRecienteDTO> obtenerUltimosGanadores(@RequestParam(defaultValue = "10") int cantidad) {
        return usuarioService.obtenerUltimosPremiosOtorgados(cantidad);
    }
    
    
}
