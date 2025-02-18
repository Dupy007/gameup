package com.gamesUP.gamesUP.dao;

import com.gamesUP.gamesUP.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Spring Data JPA générera automatiquement l'implémentation
    Optional<Client> findByEmail(String email);
}
