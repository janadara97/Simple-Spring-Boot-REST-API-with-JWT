package com.training.ums.services;

import java.util.List;

import com.training.ums.entity.Role;
import com.training.ums.entity.User;
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


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        System.out.println("myy"+userName);
        System.out.println("myy"+roleName);
        User user=userRepository.findByUserName(userName);
        Role role=roleRepository.findByName(roleName);
        

        //user.getRoles().add(role);
        System.out.println("jana"+user);
        role.getUsers().add(user);
        userRepository.save(user);
       
        
        
    }

    @Override
    public User getUser(String userName) {
        
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    
    
}
