package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avis {
    String commentaire;
    int note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    Game game;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
