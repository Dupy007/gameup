package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
public class Category {

    String type;
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    List<Game> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
