package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WishLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;
    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    @JsonIgnore
    private Wishlist wishlist;
}
