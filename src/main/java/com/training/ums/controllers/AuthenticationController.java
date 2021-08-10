package com.training.ums.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.training.ums.security.MyUserDetails;
import com.training.ums.security.MyUserServiceDetails;
import com.training.ums.security.authentication_models.AuthenticationRequest;
import com.training.ums.security.authentication_models.AuthenticationResponse;
import com.training.ums.security.util.JwtUtil;
import com.training.ums.security.util.JwtUtils2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserServiceDetails myUserServiceDetails;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtUtils2 jwtUtil2;

    

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try{
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
        );}
        catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password"+e);
        }
        Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil2.generateJwtToken(authentication);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new AuthenticationResponse(jwt,roles));
        // final UserDetails userDetails=myUserServiceDetails.loadUserByUsername(authenticationRequest.getUserName());
        // final String jwt=jwtUtil.generateToken(userDetails);
        // return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
    
}
