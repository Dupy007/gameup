package com.gamesUP.gamesUP.dao;

import com.gamesUP.gamesUP.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    Optional<Avis> findByUser_Id(Long userId);

    Optional<Avis> findByGame_Id(long gameId);
}
