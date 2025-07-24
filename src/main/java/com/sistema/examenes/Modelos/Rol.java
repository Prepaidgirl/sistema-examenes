package com.sistema.examenes.Modelos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
