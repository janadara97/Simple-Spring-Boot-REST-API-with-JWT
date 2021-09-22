package com.training.ums.repository;

import com.training.ums.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String UserName);
    User deleteById(long id);
    
}
