package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Wishlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@OneToMany(mappedBy = "wishlist")
    List<WishLine> line;
	@OneToOne
	@JoinColumn(name = "client_id")
	Client client;
}
