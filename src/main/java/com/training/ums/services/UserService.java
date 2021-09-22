package com.training.ums.services;

import java.util.List;

import com.training.ums.entity.Privilege;
import com.training.ums.entity.Role;
import com.training.ums.entity.User;



public interface UserService {

    User saveUser(User user);
    Role saveRole(Role Role);
    Boolean addRoleToUser(String userName,String roleName);
    User getUser(String username);
    List <User> getUsers();
    Privilege addPrivilege(Privilege privilege);
    Boolean deletePrivilege(int id);
    Boolean addRoleToPrivilege(String roleName,String privilegeName);
    User deleteUser(long  id);
    List<Privilege> getPrivileges();

    
}
