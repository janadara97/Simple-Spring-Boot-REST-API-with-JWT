package com.training.ums.security.authentication_models;

import java.util.List;

public class AuthenticationResponse {

    private final String jwt;
    private List<String> roles;

    

    

    public AuthenticationResponse(String jwt, List<String> roles) {
        this.jwt = jwt;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    
    
    
}
