package com.sistema.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.examenes.Modelos.Rol;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Long> {
    
}
