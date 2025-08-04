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

import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuario guardado exitosamente"),
        @ApiResponse(code = 400, message = "Error al guardar el usuario"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
      Set<UsuarioRol> roles = new HashSet<>();

      Rol rol = new Rol();
      rol.setRolid(2L);
      rol.setNombre("Normal");

      UsuarioRol usuarioRol = new UsuarioRol();
      usuarioRol.setUsuario(usuario);
      usuarioRol.setRol(rol);

      roles.add(usuarioRol); 

      return usuariosService.guardarUsuario(usuario, roles);
    } 

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de usuarios obtenida exitosamente"),
        @ApiResponse(code = 400, message = "No se encontraron usuarios"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username")String username){
      return usuariosService.obtenerUsuario(username);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de usuarios obtenida exitosamente"),
        @ApiResponse(code = 400, message = "No se encontraron usuarios"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId")long usuarioId) {
      usuariosService.eliminarUsuario(usuarioId); 

}
}