package com.training.ums.security.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import com.training.ums.entity.Role;
import com.training.ums.entity.User;
import com.training.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
    @Autowired
    UserRepository userRepository;

    private String SECRET_KEY="secret";


    public String extractuserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
   

    public <T> T extractClaim(String token,Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean  isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        Set<String> userRoles = new HashSet<>();
        User user=userRepository.findByUserName(userDetails.getUsername());
        for(Role role:user.getRoles()){
            userRoles.add(role.getName());
        }
        claims.put("Roles", userRoles.toArray());
        return createToken(claims, userDetails.getUsername());
    }

    public String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
        
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        
        
    }
    public Boolean validateToken(String token,UserDetails userDetails){
        final String username=extractuserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
}
