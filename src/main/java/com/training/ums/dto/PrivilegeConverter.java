package com.training.ums.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.training.ums.entity.Privilege;

import org.springframework.stereotype.Component;

@Component
public class PrivilegeConverter {
    

    public PrivilegeDto entityToDto(Privilege privilege){
        PrivilegeDto privilegeDto = new PrivilegeDto();
        privilegeDto.setId(privilege.getId());
        privilegeDto.setPrivilegeName(privilege.getPrivilegeName());
        
        if(privilege.getRoles()==null){
            return privilegeDto;
        }
        else{
            Set <String> roleNames=privilege.getRoles().stream()
                                            .map((x)->x.getName()).collect(Collectors.toSet());
            privilegeDto.setRoles(roleNames);
            return privilegeDto;
        }
        
    }

    public List<PrivilegeDto> entityToDto(List<Privilege> roles){
        return roles.stream().map((x)->entityToDto(x)).collect(Collectors.toList());
    }

    public Privilege dtoToEntity(PrivilegeDto privilegeDto){
        Privilege privilege=new Privilege();
        privilege.setId(privilegeDto.getId());
        privilege.setPrivilegeName(privilegeDto.getPrivilegeName());;
      
        return privilege;
    }

    public List<Privilege> dtoToEntity(List<PrivilegeDto> privilegeDtos){
       return privilegeDtos.stream().map((x)->dtoToEntity(x)).collect(Collectors.toList());
    }
}
