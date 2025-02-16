package com.gamesUP.gamesUP.dao;

import com.gamesUP.gamesUP.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    Optional<Wishlist> findByClient_Id(Long clientId);
}
