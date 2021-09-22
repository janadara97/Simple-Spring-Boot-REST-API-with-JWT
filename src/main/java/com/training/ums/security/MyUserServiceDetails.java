package com.training.ums.security;



import com.training.ums.entity.User;
import com.training.ums.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserServiceDetails implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
      
      User user=userRepository.findByUserName(username);
      return MyUserDetails.build(user);
      
       

    }
    
}
