package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	String commentaire;

	int note;
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	@ManyToOne
	@JoinColumn(name = "game_id")
	Game game;
}
