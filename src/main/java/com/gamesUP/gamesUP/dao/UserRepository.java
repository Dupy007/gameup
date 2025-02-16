package com.gamesUP.gamesUP.dao;

import com.gamesUP.gamesUP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // Spring Data JPA générera automatiquement l'implémentation
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
