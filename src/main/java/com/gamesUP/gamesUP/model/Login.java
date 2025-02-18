package com.gamesUP.gamesUP.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Login {
    private String motdepasse;
    @Column(unique = true)
    private String username;
}
