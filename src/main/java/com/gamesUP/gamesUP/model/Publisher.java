package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Publisher {

	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	String name;
	@OneToMany(mappedBy = "publisher")
	List<Game> games;

}
