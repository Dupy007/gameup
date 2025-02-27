package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PurchaseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantite;
    private double prixTT;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnore
    private Purchase purchase;

    public void setPrixTT() {
        this.prixTT = game.getPrix() * getQuantite();
    }
}
