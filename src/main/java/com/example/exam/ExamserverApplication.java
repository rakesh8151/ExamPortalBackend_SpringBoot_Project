package com.example.exam;

import com.example.exam.entities.Role;
import com.example.exam.entities.User;
import com.example.exam.entities.UserRole;
import com.example.exam.exceptions.UserFoundException;
import com.example.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("starting code");

        try {
            User user = new User();
            user.setFirstName("Rakesh");
            user.setLastName("Kumar");
            user.setUsername("rakesh123");
            user.setPassword(bCryptPasswordEncoder.encode("rakesh"));
            user.setEmail("rakesh@gmail.com");
            user.setProfile("default.png");

            Role role = new Role();
            role.setRoleName("ADMIN");

            Set<UserRole> userRoles = new HashSet<>();
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            userRoles.add(userRole);

            User user1 = userService.createUser(user, userRoles);

            System.out.println(user1.getUsername());
        }catch (UserFoundException e){
            e.printStackTrace();
        }



    }
}
