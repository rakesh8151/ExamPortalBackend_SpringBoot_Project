package com.example.exam.services.impl;

import com.example.exam.entities.User;
import com.example.exam.entities.UserRole;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.exceptions.UserFoundException;
import com.example.exam.repositories.RoleRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        //first check user already present or not in db

        User local=userRepository.findByUsername(user.getUsername());
        if(local!=null){
            System.out.println("user is already present");
            throw  new UserFoundException();
        }
        else {
            //user create
            for (UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
          local=  userRepository.save(user);
        }

      return  local;
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        User user=userRepository.findByUsername(username);
        if(user==null){
            System.out.println("user is not present");
            throw  new ResourceNotFoundException("user is not present");
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users=userRepository.findAll();
        return users;
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user wit this id "+userId+" not present."));
        userRepository.delete(user);
    }
}
