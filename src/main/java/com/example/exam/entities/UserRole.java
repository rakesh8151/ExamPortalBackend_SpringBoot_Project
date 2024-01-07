package com.example.exam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long userRoleId;

    //user
    @ManyToOne(fetch = FetchType.EAGER)
    private  User user;


    @ManyToOne(fetch = FetchType.EAGER)
    private  Role role;

}
