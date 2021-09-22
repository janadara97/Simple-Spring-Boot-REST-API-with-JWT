package com.training.ums.services;

import java.util.List;

import com.training.ums.entity.Privilege;
import com.training.ums.entity.Role;
import com.training.ums.entity.User;
import com.training.ums.repository.PrivilegeRepository;
import com.training.ums.repository.RoleRepository;
import com.training.ums.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Boolean addRoleToUser(String userName, String roleName) {
        try{
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);

        role.getUsers().add(user);
        userRepository.save(user);
        return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public User getUser(String userName) {

        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Privilege addPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Boolean deletePrivilege(int id) {
        try {
            privilegeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
        

    }

    @Override
    public Boolean addRoleToPrivilege(String roleName, String privilegeName) {
        try {
            Role role = roleRepository.findByName(roleName);

        Privilege privilege = privilegeRepository.findByPrivilegeName(privilegeName);

        role.getPrivileges().add(privilege);

        roleRepository.save(role);
        return true;

        } catch (Exception e) {
            return false;
        }

        

    }

    @Override
    public User deleteUser(long  id) {
        return userRepository.deleteById(id);
    }

    @Override
    public List<Privilege> getPrivileges( ) {
        return privilegeRepository.findAll();
    }

}
