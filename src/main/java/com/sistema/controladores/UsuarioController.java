package com.sistema.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.*;

import com.sistema.Repositorio.RolRepository;
import com.sistema.Servicios.UsuariosService;
import com.sistema.examenes.Modelos.Rol;
import com.sistema.examenes.Modelos.Usuario;
import com.sistema.examenes.Modelos.UsuarioRol;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(
    origins = {"http://localhost:4200", "http://192.168.1.241:4200"},
    allowCredentials = "true"
)
public class UsuarioController {

    private final UsuariosService usuariosService;
    private final RolRepository rolRepository;

    public UsuarioController(UsuariosService usuariosService, RolRepository rolRepository) {
        this.usuariosService = usuariosService;
        this.rolRepository = rolRepository;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario guardado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al guardar el usuario"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");
        Set<UsuarioRol> roles = new HashSet<>();

        Rol rol = rolRepository.findById(2L).orElse(null);
        if (rol == null) {
            rol = new Rol();
            rol.setRolId(2L);
            rol.setNombre("Normal");
            rol = rolRepository.save(rol);
        }

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        roles.add(usuarioRol);

        return usuariosService.guardarUsuario(usuario, roles);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuariosService.obtenerUsuario(username);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") long usuarioId) {
        usuariosService.eliminarUsuario(usuarioId);
    }
}
