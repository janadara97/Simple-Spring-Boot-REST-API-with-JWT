package com.training.ums.dto;

import java.util.Set;

public class PrivilegeDto {

    private int id;
    private String privilegeName;
    private Set<String> roles;

    
    public PrivilegeDto() {
    }

    

    public PrivilegeDto(int id, String privilegeName, Set<String> roles) {
        this.id = id;
        this.privilegeName = privilegeName;
        this.roles = roles;
    }



    public int getId() {
        return id;
    }


    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    
    
}
