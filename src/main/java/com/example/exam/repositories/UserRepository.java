package com.example.exam.repositories;

import com.example.exam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

   public User findByUsername(String username);
}
