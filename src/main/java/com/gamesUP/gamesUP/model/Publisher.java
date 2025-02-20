package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Publisher {


    String name;
    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    List<Game> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
