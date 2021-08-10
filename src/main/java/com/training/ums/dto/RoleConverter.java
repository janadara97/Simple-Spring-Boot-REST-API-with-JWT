package com.training.ums.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.training.ums.entity.Role;

import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    

    public RoleDto entityToDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        
        if(role.getUsers()==null){
            return roleDto;
        }
        else{
            Set <String> roleNames=role.getUsers().stream()
                                            .map((x)->x.getName()).collect(Collectors.toSet());
        roleDto.setUsers(roleNames);
        return roleDto;
        }
        
    }

    public List<RoleDto> entityToDto(List<Role> roles){
        return roles.stream().map((x)->entityToDto(x)).collect(Collectors.toList());
    }

    public Role dtoToEntity(RoleDto roleDto){
        Role role=new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
      
        return role;
    }

    public List<Role> dtoToEntity(List<RoleDto> roleDtos){
       return roleDtos.stream().map((x)->dtoToEntity(x)).collect(Collectors.toList());
    }

}
