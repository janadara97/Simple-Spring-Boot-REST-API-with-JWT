package com.training.ums.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.training.ums.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    PasswordEncoder passwordEncoder;
    

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUserName((user.getUserName()));
       
        if(user.getRoles()==null){
            return userDto;
        }
        else{
            Set <String> roleNames=user.getRoles().stream().map((x)->x.getName()).collect(Collectors.toSet());
        userDto.setRoleNames(roleNames);
        return userDto;
        }
        
    }

    public List<UserDto> entityToDto(List<User> users){
        return users.stream().map((x)->entityToDto(x)).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }

    public List<User> dtoToEntity(List<UserDto> userDtos){
       return userDtos.stream().map((x)->dtoToEntity(x)).collect(Collectors.toList());
    }


}
