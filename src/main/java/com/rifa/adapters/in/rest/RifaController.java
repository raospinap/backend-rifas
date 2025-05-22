package com.rifa.adapters.in.rest;

import com.rifa.domain.model.Rifa;
import com.rifa.domain.ports.in.RifaService;
import com.rifa.adapters.in.rest.dto.CrearRifaDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rifas")
public class RifaController {

    private final RifaService rifaService;

    public RifaController(RifaService rifaService) {
        this.rifaService = rifaService;
    }

    // Solo un ADMIN puede crear rifas
    @PostMapping
    public ResponseEntity<?> crearRifa(@Valid @RequestBody CrearRifaDTO dto, HttpServletRequest request) {
        String rol = (String) request.getAttribute("rol");
        if (!"ADMIN".equalsIgnoreCase(rol)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo un administrador puede crear rifas.");
        }

        Rifa nueva = rifaService.crearRifa(dto.getTema(), dto.getDuracionMinutos(), dto.getNumGanadores());
        return ResponseEntity.ok(nueva);
    }

    // Solo un ADMIN puede terminar rifas
    @PatchMapping("/{id}/terminar")
    public ResponseEntity<?> terminarRifa(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean sortear,
            HttpServletRequest request) {

        String rol = (String) request.getAttribute("rol");
        if (!"ADMIN".equalsIgnoreCase(rol)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo un administrador puede terminar rifas.");
        }

        try {
            Rifa rifa = rifaService.terminarRifa(id, sortear);
            return ResponseEntity.ok(rifa); // o mapear a DTO si prefieres
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Rifas activas x usuario
    @GetMapping("/activas/usuario")
    public ResponseEntity<List<Rifa>> listarRifasActivasPorUsuario(HttpServletRequest request) {
        Long idUsuario = (Long) request.getAttribute("id");
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Rifa> rifas = rifaService.listarRifasActivasPorUsuario(idUsuario);
        return ResponseEntity.ok(rifas);
    }
   
    // Rifas disponibles para participar x usuario
    @GetMapping("/activas/disponibles")
    public ResponseEntity<List<Rifa>> listarRifasDisponibles(HttpServletRequest request) {
        Long idUsuario = (Long) request.getAttribute("id");
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Rifa> disponibles = rifaService.listarRifasDisponiblesParaUsuario(idUsuario);
        return ResponseEntity.ok(disponibles);
    }

    
    // Todos pueden ver rifas activas
    @GetMapping("/activas")
    public ResponseEntity<List<Rifa>> listarRifasActivas() {
        List<Rifa> activas = rifaService.listarRifasActivas();
        return ResponseEntity.ok(activas);
    }

    // Todos pueden ver rifas por estado
    @GetMapping
    public List<Rifa> listarPorEstado(@RequestParam("estado") Rifa.EstadoRifa estado) {
        return rifaService.listarPorEstado(estado);
    }
}
