package com.sistema.Servicios.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.Repositorio.RolRepository;
import com.sistema.Repositorio.UsuarioRepository;
import com.sistema.Servicios.UsuariosService;
import com.sistema.examenes.Modelos.Usuario;
import com.sistema.examenes.Modelos.UsuarioRol;

@Service
public class UsuarioServiceImpl  implements UsuariosService { 

    @Autowired
    private UsuarioRepository usuarioRespository;

    @Autowired
    private RolRepository rolRespository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioActual = usuarioRespository.findByUsername(usuario.getUsername());
        if(usuarioActual!=null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya Presente");
            
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRespository.save(usuarioRol.getRol());

            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioRespository.save(usuario);
            return usuario;
            
        }   

       
        
    }

    @Override
    public Usuario obtenerUsuario(String username) {
       return usuarioRespository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(long usuarioId) {
        usuarioRespository.deleteById(usuarioId);
        
    }
}

    

