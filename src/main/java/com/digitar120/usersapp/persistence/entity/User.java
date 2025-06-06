package com.digitar120.usersapp.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Definition of the User table.
 * @author Gabriel Pérez (digitar120)
 */
@Entity @Table(name = "USER")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class User {
    /**
     * ID representing an individual person's identifying number. Must be managed manually.
     */
    @Id @Column(name = "USER_ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_LASTNAME")
    private String lastName;

    public User (Integer id){ this.id = id;}
    public User (String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }
}
