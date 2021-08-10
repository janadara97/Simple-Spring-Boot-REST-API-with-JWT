package com.training.ums.repository;

import com.training.ums.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String UserName);
    
}
