package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    
    User findByResetToken(String token);

//    void delete(Optional<User> resetToken);
}
