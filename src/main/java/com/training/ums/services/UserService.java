package com.training.ums.services;

import java.util.List;

import com.training.ums.entity.Role;
import com.training.ums.entity.User;



public interface UserService {

    User saveUser(User user);
    Role saveRole(Role Role);
    void addRoleToUser(String userName,String roleName);
    User getUser(String username);
    List <User> getUsers();

    
}
