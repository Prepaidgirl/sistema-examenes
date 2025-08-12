package com.sistema.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.Servicios.Impl.UserDetailsServiceImpl;
import com.sistema.configuraciones.JwtUtils;
import com.sistema.examenes.Modelos.JwtRequest;
import com.sistema.examenes.Modelos.JwtResponse;
import com.sistema.examenes.Modelos.Usuario;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) {
        try {
            // Paso 1: Autenticar al usuario
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());

            // Paso 2: Cargar detalles del usuario
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());

            // Paso 3: Generar token
            String token = this.jwtUtils.generateToken(userDetails);
            System.out.println("TOKEN GENERADO: " + token); // Para depuración

            return ResponseEntity.ok(new JwtResponse(token));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Usuario no encontrado");

        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Credenciales inválidas");

        } catch (DisabledException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Usuario deshabilitado");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("Usuario deshabilitado", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales inválidas", e);
        }
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal) {
        return (Usuario) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}
