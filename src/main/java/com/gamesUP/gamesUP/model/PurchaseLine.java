package com.gamesUP.gamesUP.model;

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
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    public void setPrixTT() {
        this.prixTT = game.getPrix()*getQuantite();
    }
}
