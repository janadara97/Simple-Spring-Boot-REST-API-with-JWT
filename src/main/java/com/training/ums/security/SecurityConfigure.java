package com.training.ums.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.training.ums.entity.Privilege;
import com.training.ums.entity.Role;
import com.training.ums.security.filters.AuthTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
securedEnabled = true
//jsr250Enabled = true,
//prePostEnabled = true
)


public class SecurityConfigure extends WebSecurityConfigurerAdapter{

    @Autowired
    private MyUserServiceDetails myUserServiceDetails;
    // @Autowired
    // private JwtRequestFilter jwtRequestFilter;

    @Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserServiceDetails);

    
    }

    


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/user/getUsers").hasAuthority("Read_User_Data")
        .antMatchers("/user/addUser").hasAuthority("Write_User_Data")
        .antMatchers("/user/addRole").hasAuthority("Write_User_Data")
        .antMatchers("/user/addRoleToUser").hasAuthority("Write_User_Data")
        .antMatchers("/user/addPrivileges").hasAuthority("Write_User_Data")
        .antMatchers("/user/deletePrivilege").hasAuthority("Delete_User_Data")
        .antMatchers("/user/addRoleToPrivilege").hasAuthority("Write_User_Data")

        .antMatchers("/lecturers/getAllLect").hasAuthority("Read_Lecturer_Data")
        .antMatchers("/lecturers/addLect").hasAuthority("Write_User_Data")
        .antMatchers("/lecturers/updateLect/**").hasAuthority("Write_User_Data")
        .antMatchers("/lecturers/deleteLect/**").hasAuthority("Delete_User_Data")
        .antMatchers("/getModules").hasAuthority("Write_Lecturer_Data")
        .antMatchers("/addModules/**").hasAuthority("Write_Lecturer_Data")
        .antMatchers("/updateModule/**").hasAuthority("Write_Lecturer_Data")
        .antMatchers("/deleteModule/**").hasAuthority("Delete_Lecturer_Data")
        
        .antMatchers("/students/getAllModules").hasAuthority("Read_Student_Data")
        .antMatchers("/students/getAllLect").hasAuthority("Read_Student_Data")
        .antMatchers("/students/getAllTopics").hasAuthority("Read_Student_Data")
        .antMatchers("/students/getTopics/**").hasAuthority("Read_Student_Data")
        
       
       
        
        .antMatchers("/authenticate").permitAll()
        // .antMatchers("/user/**").permitAll()
        // .antMatchers("/getAllTopics").permitAll()
        // .antMatchers("/getModules").permitAll()
        // .antMatchers("/addModules").permitAll()
        // .antMatchers("/deleteModule/**").permitAll()
        // .antMatchers("/updateModule/**").permitAll()

         
        
        .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // http.authorizeRequests()
        
        
       
        
    }
   
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

   

    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchyImpl=new RoleHierarchyImpl();
        String hierarchy="ROLE_ADMIN>ROLE_LECTURER \n ROLE_LECTURER>ROLE_STUDENT";
        roleHierarchyImpl.setHierarchy(hierarchy);
        return roleHierarchyImpl;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler=new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy((roleHierarchy()));
        return defaultWebSecurityExpressionHandler;
    }
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    
    
}
