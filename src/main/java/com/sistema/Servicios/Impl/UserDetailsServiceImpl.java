package com.sistema.Servicios.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.Repositorio.UsuarioRepository;
import com.sistema.examenes.Modelos.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = this.usuarioRepository.findByUsername(username);
       if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado ");
       }
       return usuario;
    }
}