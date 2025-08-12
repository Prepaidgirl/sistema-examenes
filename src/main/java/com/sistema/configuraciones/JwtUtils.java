package com.sistema.configuraciones;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    // Clave secreta mínima de 32 caracteres
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(
        "clave-super-secreta-para-jwt-1234567890".getBytes()
    );

    // Generar token
    public String generateToken(UserDetails userDetails) {
        System.out.println("UserDetails username: " + userDetails.getUsername());

        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, userDetails.getUsername());

        System.out.println("Token generado: " + token);

        return token;
    }

    // Crear token con claims y fechas
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Extraer usuario del token
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Verificar expiración
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
