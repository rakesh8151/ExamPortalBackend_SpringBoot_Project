package com.example.exam.services;

import com.example.exam.entities.User;
import com.example.exam.entities.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    //creating user

     User createUser(User user, Set<UserRole>userRoles) throws Exception;

     User getUserByUsername(String username) throws Exception;

     List<User> getAllUser();

     void deleteUser(Long userId) throws Exception;

}
