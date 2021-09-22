package com.training.ums.controllers;


import java.util.List;

import java.util.stream.Collectors;


import com.training.ums.repository.RoleRepository;
import com.training.ums.repository.UserRepository;
import com.training.ums.security.MyUserDetails;
import com.training.ums.security.MyUserServiceDetails;
import com.training.ums.security.authentication_models.AuthenticationRequest;
import com.training.ums.security.authentication_models.AuthenticationResponse;

import com.training.ums.security.util.JwtUtils2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080/")
//@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserServiceDetails myUserServiceDetails;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

 

    @Autowired
    JwtUtils2 jwtUtil2;

    @Autowired
    PasswordEncoder passwordEncoder;

    

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        
      

        try{
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword() )


        );
    
    }
        catch(BadCredentialsException e){
            return ResponseEntity.ok().body(new AuthenticationResponse(null, null, false, "Username or Password is Invalid", null));
        }
        Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil2.generateJwtToken(authentication);
        String userName=jwtUtil2.getUserNameFromJwtToken(jwt);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();		
		List<String> privileges = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

       
		return ResponseEntity.ok(new AuthenticationResponse(jwt, userName, true, "User Logged in Successfully", privileges));
      

    }

    
}
