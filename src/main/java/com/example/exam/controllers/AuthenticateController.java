package com.example.exam.controllers;

import com.example.exam.config.JwtUtils;
import com.example.exam.entities.JwtRequest;
import com.example.exam.entities.JwtResponse;
import com.example.exam.entities.User;
import com.example.exam.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw  new Exception("user not found");
        }
        //authenticated user

        String jwtToken= jwtUtils.generateToken(userDetailsService.loadUserByUsername(jwtRequest.getUsername()));

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    private  void authenticate(String username,String password) throws Exception {

        try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("user is disabled "+e.getMessage());
        }
        catch (BadCredentialsException e){
            throw new Exception("invalid credentials "+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(Principal principal){
        User user= (User) userDetailsService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }

}
