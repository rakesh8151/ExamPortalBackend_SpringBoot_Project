package com.example.exam.config;

import com.example.exam.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      final  String requestTokenHeader= request.getHeader("Authorization");
      String username=null;
      String jwtToken=null;
      if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")){
          jwtToken=requestTokenHeader.substring(7);
         try {
              username = jwtUtil.getUsernameFromToken(jwtToken);
          }catch (ExpiredJwtException e){
             e.printStackTrace();
             System.out.println(" jwt token is expired");
         }
         catch (Exception e){
             e.printStackTrace();
             System.out.println(" error");
         }
          if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            final  UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwtToken,userDetails)){
                //valid token
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
          }else {
              System.out.println("token is not valid");
          }

      }
      else{
          System.out.println("Invalid token, not start with Bearer");
      }
      filterChain.doFilter(request,response);
    }
}
