package com.training.ums.dto;

import java.util.Set;

public class RoleDto {
    private Long id;
    private String name;
    private Set<String> users;
    
    public RoleDto() {
        
    }

    public RoleDto(Long id, String name, Set<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<String> getUsers() {
        return users;
    }
    public void setUsers(Set<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleDto [id=" + id + ", name=" + name + ", users=" + users + "]";
    }

    
    
}
