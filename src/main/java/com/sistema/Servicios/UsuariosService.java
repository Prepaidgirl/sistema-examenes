package com.sistema.Servicios;


import java.util.Set;

import com.sistema.examenes.Modelos.Usuario;
import com.sistema.examenes.Modelos.UsuarioRol;


public interface UsuariosService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol>usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username );

    public void eliminarUsuario(long usuarioId);
} 

