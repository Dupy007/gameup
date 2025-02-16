package com.gamesUP.gamesUP.model;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class Login {
    @Column(unique = true)
    private String email;
    private String motdepasse;
    private String username;
}
