package com.sistema;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.Servicios.UsuariosService;
import com.sistema.examenes.Modelos.Rol;
import com.sistema.examenes.Modelos.Usuario;
import com.sistema.examenes.Modelos.UsuarioRol;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {


     @Autowired
    private UsuariosService usuariosService;

    public static void main(String[] args) {
        SpringApplication.run(SistemaExamenesBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
         Usuario usuario = new Usuario();

         usuario.setNombre("Andres");
         usuario.setApellido("Castro");
         usuario.setEmail("a1@gmail.com");
         usuario.setPassword("123456");
         usuario.setUsername("andres");
         usuario.setTelefono("123456789");
         usuario.setPerfil("algo");

        Rol rol = new Rol();
        rol.setNombre("Admin");

        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        UsuarioRol usuarioRol = new UsuarioRol(); // <-- Add this line
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);
        

        usuarioRoles.add(usuarioRol);


        Usuario usuarioGuardado = usuariosService.guardarUsuario(usuario, usuarioRoles);
        System.out.println(usuarioGuardado.getUsername()); 

    }
    
}

 
