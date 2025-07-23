package com.sistema.examenes.Modelos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rolid;
    private String nombre;


    @OneToMany (cascade=CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="rol")
    private Set<UsuarioRol>usuarioRoles=new HashSet<>();



    public long getRolid() {
        return rolid;
    }


    public void setRolid(long rolid) {
        this.rolid = rolid;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }


    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
    
    public Rol(){
        
    }
}
