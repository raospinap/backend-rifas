package com.rifa.adapters.in.rest;

import com.rifa.domain.ports.in.VerificacionCorreoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rifa.adapters.in.rest.dto.CorreoDTO;
import com.rifa.adapters.in.rest.dto.VerificacionDTO;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/verificacion")
public class VerificacionCorreoController {

    private final VerificacionCorreoService service;

    // Dominios permitidos (temporalmente configurados aquí)
    private static final List<String> DOMINIOS_PERMITIDOS = List.of("@ut.edu.co", "@conactic.org");

    public VerificacionCorreoController(VerificacionCorreoService service) {
        this.service = service;
    }

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarCodigo(@Valid @RequestBody CorreoDTO correoDTO) {
        String correo = correoDTO.getCorreo().trim().toLowerCase();

        if (!esCorreoPermitido(correo)) {
            return ResponseEntity.badRequest().body("Dominio de correo no autorizado.");
        }

        service.enviarCodigoOTP(correo);
        return ResponseEntity.ok("Código enviado a " + correo);
    }


    @PostMapping("/verificar")
    public ResponseEntity<?> verificarCodigo(@Valid @RequestBody VerificacionDTO dto) {
        String correo = dto.getCorreo().trim().toLowerCase();
        String codigo = dto.getCodigo().trim();

        boolean valido = service.verificarCodigo(correo, codigo);
        if (valido) {
            return ResponseEntity.ok("Código válido.");
        } else {
            return ResponseEntity.status(403).body("Código inválido, expirado o ya usado.");
        }
    }


    private boolean esCorreoPermitido(String correo) {
    	correo = correo.trim().toLowerCase();
        return DOMINIOS_PERMITIDOS.stream().anyMatch(correo::endsWith);
    }
}
