package com.training.ums.security;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;



import com.training.ums.entity.Privilege;
import com.training.ums.entity.Role;
import com.training.ums.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;



public class MyUserDetails implements UserDetails{

   
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(String username,String password,Collection<? extends GrantedAuthority> authorities){

        this.username = username;
		this.password = password;
		this.authorities = authorities;
    }


    @Transactional
    public static MyUserDetails build (User user){
       
        Collection<Role> roles=user.getRoles();
        List<String> privileges = new ArrayList<>();
          List<Privilege> collection = new ArrayList<>();
          for (Role role : roles) {
              privileges.add(role.getName());
              collection.addAll(role.getPrivileges());
          }
          for (Privilege item : collection) {
              privileges.add(item.getPrivilegeName());
          }
          List<GrantedAuthority> authorities = new ArrayList<>();
          for (String privilege : privileges) {
              authorities.add(new SimpleGrantedAuthority(privilege));
          }
        
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
