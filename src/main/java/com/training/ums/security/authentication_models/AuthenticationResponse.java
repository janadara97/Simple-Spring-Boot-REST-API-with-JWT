package com.training.ums.security.authentication_models;

import java.util.List;

public class AuthenticationResponse {

    private final String jwt;
    private final String UserName;
    private  final Boolean success;
    private final String message;
    private final List<String> privileges;

    

    
    

    

    public AuthenticationResponse(String jwt, String userName, Boolean success, String message,
            List<String> privileges) {
        this.jwt = jwt;
        UserName = userName;
        this.success = success;
        this.message = message;
        this.privileges = privileges;
    }

    public Boolean getSuccess() {
        return success;
    }

   

    public String getMessage() {
        return message;
    }

   
    public List<String> getPrivileges() {
        return privileges;
    }

    

   

    

   

    public String getJwt() {
        return jwt;
    }

    public String getUserName() {
        return UserName;
    }

    
    
    
}
