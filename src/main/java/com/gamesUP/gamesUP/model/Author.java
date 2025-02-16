package com.gamesUP.gamesUP.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Entity
@DiscriminatorValue("AUTHOR")
@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends User {

    @OneToMany(mappedBy = "author")  // Assurez-vous d'avoir la bonne relation côté Game
    private List<Game> games;
}
