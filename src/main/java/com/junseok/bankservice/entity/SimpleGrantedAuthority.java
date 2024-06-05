package com.junseok.bankservice.entity;

import org.springframework.security.core.GrantedAuthority;

public final class SimpleGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}