package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	Date date;
	boolean paid;
	boolean delivered;
	boolean archived;
	@OneToMany(mappedBy = "purchase")
	List<PurchaseLine> line;
	@ManyToOne
	@JoinColumn(name = "client_id")
	Client client;
}
