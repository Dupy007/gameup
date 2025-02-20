package com.gamesUP.gamesUP.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ADMIN")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String motdepasse;

    @Column(name = "role", insertable = false, updatable = false)
    private String role;
}