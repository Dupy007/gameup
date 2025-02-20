package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Wishlist {


    @OneToMany(mappedBy = "wishlist")
    @JsonIgnore
    List<WishLine> line;
    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    Client client;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
