package com.sistema.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.Servicios.UsuariosService;
import com.sistema.examenes.Modelos.Rol;
import com.sistema.examenes.Modelos.Usuario;
import com.sistema.examenes.Modelos.UsuarioRol;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;


    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
      Set<UsuarioRol> roles = new HashSet<>();

      Rol rol = new Rol();
      rol.setRolid(2L);
      rol.setNombre("Normal");

      UsuarioRol usuarioRol = new UsuarioRol();
      usuarioRol.setUsuario(usuario);
      usuarioRol.setRol(rol);

      return usuariosService.guardarUsuario(usuario, roles);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username")String username){
      return usuariosService.obtenerUsuario(username);
    
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId")long usuarioId) {
      usuariosService.eliminarUsuario(usuarioId); 

}
}