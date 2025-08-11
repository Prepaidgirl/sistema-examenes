package com.sistema.examenes.Modelos;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    public Authority(String authority) {
    }

    @Override
    public String getAuthority() {
        return null;


    }

}
