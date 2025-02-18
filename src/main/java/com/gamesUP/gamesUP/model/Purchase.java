package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Purchase {

    Date date;
    boolean paid;
    boolean delivered;
    boolean archived;
    @OneToMany(mappedBy = "purchase")
    @JsonBackReference
    List<PurchaseLine> line;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    Client client;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
