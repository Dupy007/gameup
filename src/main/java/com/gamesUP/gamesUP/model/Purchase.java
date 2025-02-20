package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    List<PurchaseLine> line;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    Client client;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
