package com.rifa.adapters.in.rest;

import com.rifa.adapters.in.rest.dto.CorreoDTO;
import com.rifa.adapters.out.external.JwtService;
import com.rifa.domain.model.Usuario;
import com.rifa.domain.ports.in.UsuarioService;
import java.util.Collections;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public AuthController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody CorreoDTO dto) {
        String correo = dto.getCorreo().trim().toLowerCase();

        Usuario usuario = usuarioService.crearUsuarioSiNoExiste(correo, null);

        if (usuario.getEstado() != Usuario.EstadoUsuario.ACTIVO) {
            return ResponseEntity.status(403).body("Usuario inactivo.");
        }

        String token = jwtService.generarToken(usuario);


        return ResponseEntity.ok(Collections.singletonMap("token", token));

    }
}
