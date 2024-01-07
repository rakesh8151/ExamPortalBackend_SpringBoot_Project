package com.example.exam.controllers;

import com.example.exam.entities.Role;
import com.example.exam.entities.User;
import com.example.exam.entities.UserRole;
import com.example.exam.payloads.ApiResponse;
import com.example.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        user.setProfile("default.png");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> userRoles=new HashSet<>();
        Role role=new Role();
        role.setRoleName("NORMAL");
        UserRole userRole=new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoles.add(userRole);
        user.getUserRoles().addAll(userRoles);
        User createUser=userService.createUser(user,userRoles);
        return  ResponseEntity.ok(createUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws Exception {
        User user=userService.getUserByUsername(username);
        return  ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUserByUsername() {
        List<User> users=userService.getAllUser();
        return  ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long userId) throws Exception {
        userService.deleteUser(userId);
       return ResponseEntity.ok(new ApiResponse("user is deleted successfully.. ",true, HttpStatus.OK));
    }
}
