package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    Game game;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
