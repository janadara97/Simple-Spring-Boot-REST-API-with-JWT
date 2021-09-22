// package com.training.ums.services;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.List;

// import javax.transaction.Transactional;

// import com.training.ums.entity.Privilege;
// import com.training.ums.entity.Role;
// import com.training.ums.entity.User;
// import com.training.ums.repository.PrivilegeRepository;
// import com.training.ums.repository.RoleRepository;
// import com.training.ums.repository.UserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationListener;
// import org.springframework.context.event.ContextRefreshedEvent;

// public class SeteupDataLoader implements ApplicationListener<ContextRefreshedEvent>  {

//     private boolean alreadySetup = false;
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private RoleRepository roleRepository;

//     @Autowired
//     private PrivilegeRepository privilegeRepository;

//     @Override
//     @Transactional
//     public void onApplicationEvent(final ContextRefreshedEvent event) {
//         if (alreadySetup) {
//             return;
//         }

//         // == create initial privileges
//         final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
//         final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
       

//         // == create initial roles
//         final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege));
//         final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege));
//         final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//         final Role userRole=createRoleIfNotFound("ROLE_USER", userPrivileges);

//         // == create initial user
//         createUserIfNotFound((long) 01, "Test", "test", new ArrayList<>(Arrays.asList(adminRole)));

//         alreadySetup = true;
//     }

//     @Transactional
//     Privilege createPrivilegeIfNotFound(final String name) {
//         Privilege privilege = privilegeRepository.findByPrivilegeName(name);
//         if (privilege == null) {
//             privilege = new Privilege(name);
//             privilege = privilegeRepository.save(privilege);
//         }
//         return privilege;
//     }

//     @Transactional
//     Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
//         Role role = roleRepository.findByName(name);
//         if (role == null) {
//             role = new Role(name);
//         }
//         role.setPrivileges(privileges);
//         role = roleRepository.save(role);
//         return role;
//     }

//     @Transactional
//     User createUserIfNotFound(final Long id, final String userName, final String password, final Collection<Role> roles) {
//         User user = userRepository.findByUserName(userName);
//         if (user == null) {
//             user = new User();
//             user.setUserName(userName);
//             user.setPassword(password);
//         }
//         user.setRoles(roles);
//         user = userRepository.save(user);
//         return user;
//     }
    
// }
