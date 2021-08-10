package com.training.ums.security;



import com.training.ums.entity.User;
import com.training.ums.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceDetails implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
       // System.out.println("nameeee "+username);
        User user=userRepository.findByUserName(username);
      //  Collection <SimpleGrantedAuthority> authorities=new ArrayList<>();
      //  user.getRoles().forEach(role->{authorities.add(new SimpleGrantedAuthority(role.getName()));
    
   //  });
      //  return new  org.springframework.security.core.userdetails.User (user.getName(), user.getPassword(),authorities);
      System.out.println("userrrrrrr"+user);
      return MyUserDetails.build(user);
      
      
       
       
        
    //    user.map(MyUserDetails::new).get();
    //    System.out.println("userrrrrrr"+user);
        
       
       

    }
    
}
