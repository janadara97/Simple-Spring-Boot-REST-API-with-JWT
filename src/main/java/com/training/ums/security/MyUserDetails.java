package com.training.ums.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import com.training.ums.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class MyUserDetails implements UserDetails{

   
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(String username,String password,Collection<? extends GrantedAuthority> authorities){

        this.username = username;
		this.password = password;
		this.authorities = authorities;
    }

    public static MyUserDetails build (User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
        
                return new MyUserDetails(
                    user.getUserName(), 
                    user.getPassword(), 
                    authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        return authorities;
    }


    @Override
    public String getPassword() {
        
        return password;
    }


    @Override
    public String getUsername() {
        
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
       
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }


    @Override
    public boolean isEnabled() {
       
        return true;
    }
    
    
}
