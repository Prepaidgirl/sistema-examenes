package com.sistema.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.Servicios.Impl.UserDetailsServiceImpl;
import com.sistema.configuraciones.JwtUtils;
import com.sistema.examenes.Modelos.JwtRequest;
import com.sistema.examenes.Modelos.JwtResponse;
;

@RestController
public class AuthenticationController {

   

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    

  @PostMapping("/generate-token")
public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) {
    System.out.println("=== INICIO GENERATE TOKEN ===");
    System.out.println("Username recibido: " + jwtRequest.getUsername());
    System.out.println("Password recibido: " + jwtRequest.getPassword());
    
    try {
        System.out.println("Intentando autenticar...");
        autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        System.out.println("Autenticación exitosa");
        
        System.out.println("Cargando detalles del usuario...");
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        System.out.println("Usuario cargado: " + userDetails.getUsername());
        
        System.out.println("Generando token...");
        String token = this.jwtUtils.generateToken(userDetails);
        System.out.println("Token generado: " + (token != null ? "SÍ" : "NO"));
        
        JwtResponse jwtResponse = new JwtResponse(token);
        System.out.println("Enviando respuesta...");
        
        return ResponseEntity.ok(jwtResponse);
        
    } catch (Exception e) {
        System.out.println("ERROR CAPTURADO: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}



  private void autenticar(String username, String password) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
  }

} 