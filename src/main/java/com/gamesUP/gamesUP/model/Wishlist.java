package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Wishlist {


    @OneToMany(mappedBy = "wishlist")
    @JsonBackReference
    List<WishLine> line;
    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    Client client;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
