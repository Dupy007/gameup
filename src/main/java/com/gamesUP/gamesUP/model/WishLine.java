package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WishLine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;
}
