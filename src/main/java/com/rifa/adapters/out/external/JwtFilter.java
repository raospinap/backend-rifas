package com.rifa.adapters.out.external;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Value;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final Key claveSecreta;
	public JwtFilter(@Value("${jwt.secret}") String secret) {
	    this.claveSecreta = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	 

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        try {
        	Key key = claveSecreta;
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String usuario = claims.getSubject();
            String rol = claims.get("rol", String.class);
            String id = claims.getSubject(); 

            request.setAttribute("id", Long.valueOf(id));
            request.setAttribute("rol", rol);

            
            UsernamePasswordAuthenticationToken authToken =
            		new UsernamePasswordAuthenticationToken(
            			    usuario,
            			    null,
            			    Collections.singleton(() -> "ROLE_" + rol) 
            			);


            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            System.out.println("❌ Token JWT inválido: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
