package com.training.ums.controllers;
import java.util.List;

import com.training.ums.dto.RoleConverter;
import com.training.ums.dto.RoleDto;
import com.training.ums.dto.UserConverter;
import com.training.ums.dto.UserDto;
import com.training.ums.entity.Role;
import com.training.ums.entity.RoleToUser;
import com.training.ums.entity.User;
import com.training.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserConverter userConverter;
    @Autowired
    UserService userService;
    @Autowired
    RoleConverter roleConverter;
    
    @GetMapping("/getUsers")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> getUsers(){
        List<User>list=userService.getUsers();
        return ResponseEntity.ok(userConverter.entityToDto(list));
    }

   
    @PostMapping("/addUser")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto){
        User user=null;
        try {
            user=userConverter.dtoToEntity(userDto);
            user=userService.saveUser(user);
            return ResponseEntity.ok(userConverter.entityToDto(user));
        } catch (Exception e) {
            e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }

    @PostMapping("/addRole")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto){

        Role role=null;
        try {
            role=roleConverter.dtoToEntity(roleDto);
            role=userService.saveRole(role);
            return ResponseEntity.ok(roleConverter.entityToDto(role));
           
        } catch (Exception e) {
            e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }

    @PostMapping("/addRoleToUser")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser roleToUser){
        System.out.println(roleToUser.getRoleName());
        System.out.println(roleToUser.getUserName());
        userService.addRoleToUser(roleToUser.getUserName(), roleToUser.getRoleName());
        return ResponseEntity.ok().build();
    }
}

