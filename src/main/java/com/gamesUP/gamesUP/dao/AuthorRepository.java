package com.gamesUP.gamesUP.dao;

import com.gamesUP.gamesUP.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Spring Data JPA générera automatiquement l'implémentation
    Optional<Author> findByEmail(String email);
}
