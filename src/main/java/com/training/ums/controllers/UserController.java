package com.training.ums.controllers;

import java.util.List;

import com.training.ums.apiResponse;
import com.training.ums.dto.PrivilegeConverter;
import com.training.ums.dto.PrivilegeDto;
import com.training.ums.dto.RoleConverter;
import com.training.ums.dto.RoleDto;
import com.training.ums.dto.UserConverter;
import com.training.ums.dto.UserDto;
import com.training.ums.entity.Privilege;
import com.training.ums.entity.Role;
import com.training.ums.entity.RoleToPrivilege;
import com.training.ums.entity.RoleToUser;
import com.training.ums.entity.User;
import com.training.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:8080/")
public class UserController {
    @Autowired
    UserConverter userConverter;
    @Autowired
    UserService userService;
    @Autowired
    RoleConverter roleConverter;
    @Autowired
    PrivilegeConverter privilegeConverter;

    private String dnf = "Data Not Found";
    private String fds = "Data Fetched Successfully";

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        List<User> list = userService.getUsers();
        if (list.isEmpty()) {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        return ResponseEntity.ok().body(new apiResponse(true, fds, userConverter.entityToDto(list)));
    }

    @GetMapping("/getPrivileges")
    public ResponseEntity<?> getPrivileges() {
        List<Privilege> pList = userService.getPrivileges();
        if (pList.isEmpty()) {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        return ResponseEntity.ok().body(new apiResponse(true, fds, privilegeConverter.entityToDto(pList)));
    }

    @PostMapping("/addUser")
    // @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        User user = null;
        try {
            user = userConverter.dtoToEntity(userDto);
            user = userService.saveUser(user);
            return ResponseEntity.ok()
                    .body(new apiResponse(true, "User added Successfully", userConverter.entityToDto(user)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessfull", null));
        }

    }

    @DeleteMapping("/deleteUser/{id}")
    // @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        try {

            return ResponseEntity.ok().body(new apiResponse(true, "Successfully Deleted", userService.deleteUser(id)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }

    }

    @PostMapping("/addRole")
    // @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto) {

        Role role = null;
        try {
            role = roleConverter.dtoToEntity(roleDto);
            role = userService.saveRole(role);
            return ResponseEntity.ok()
                    .body(new apiResponse(true, "Role Added Successfully", roleConverter.entityToDto(role)));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }

    }

    @PostMapping("/addRoleToUser")
    // @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser roleToUser) {

        Boolean add = userService.addRoleToUser(roleToUser.getUserName(), roleToUser.getRoleName());
        if (add == true) {
            return ResponseEntity.ok().body(new apiResponse(true, "Role Successfully added to the User", null));
        } else {

            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }
    }

    @PostMapping("/addPrivileges")
    public ResponseEntity<?> addPriilege(@RequestBody PrivilegeDto privilegeDto) {
        Privilege privilege2 = null;
        try {
            privilege2 = privilegeConverter.dtoToEntity(privilegeDto);
            privilege2 = userService.addPrivilege(privilege2);
            return ResponseEntity.ok().body(
                    new apiResponse(true, "Privilege Added Successfully", privilegeConverter.entityToDto(privilege2)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }

    }

    @DeleteMapping("/deletePrivilege/{pId}")
    public ResponseEntity<?> deletePrivilege(@PathVariable int pId) {
        Boolean del = userService.deletePrivilege(pId);
        if (del == true) {
            return ResponseEntity.ok().body(new apiResponse(true, "Successfully Deleted", null));
        } else {
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }
    }

    @PostMapping("/addRoleToPrivilege")
    public ResponseEntity<?> addRoleToPrivilege(@RequestBody RoleToPrivilege roleToPrivilege) {

        Boolean add = userService.addRoleToPrivilege(roleToPrivilege.getRoleName(), roleToPrivilege.getPrivilegeName());
        if (add == true) {
            return ResponseEntity.ok().body(new apiResponse(true, "Added Successfully", null));
        } else {
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }
    }

}
