package com.training.ums.dto;

import java.util.Set;

public class UserDto {
    private long id;
    private String name;
    private String userName;
    private String password;
    private Set<String> roleNames;

    
     
    public UserDto() {
      
    }
    public UserDto(long id, String name, String userName, String password, Set<String> roleNames) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.roleNames = roleNames;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<String> getRoleNames() {
        return roleNames;
    }
    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }
    @Override
    public String toString() {
        return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", roleNames=" + roleNames
                + ", userName=" + userName + "]";
    }

    

    
    
}
