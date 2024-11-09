package com.meals.meals_app.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {
    private final String userId;

    public FirebaseAuthenticationToken(String userId){
        super(Collections.emptyList());
        this.userId = userId;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}
