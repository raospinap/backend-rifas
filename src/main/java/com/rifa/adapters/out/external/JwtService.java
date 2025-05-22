package com.rifa.adapters.out.external;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rifa.domain.model.Usuario;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

	private final Key claveSecreta;
	
    private static final long DURACION_TOKEN_MS = 43200000; // 12 horas

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.claveSecreta = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    
    public String generarToken(Usuario usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + DURACION_TOKEN_MS);


        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))
                .claim("correo", usuario.getCorreo())
                .claim("rol", usuario.getRol().name())
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(claveSecreta)
                .compact();
    }
}
