package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String nom;
    public String genre;
    public int numEdition;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "author_id" )
    public Author author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    public Publisher publisher;
    @OneToMany(mappedBy = "game")
    public List<Avis> avis;
}
