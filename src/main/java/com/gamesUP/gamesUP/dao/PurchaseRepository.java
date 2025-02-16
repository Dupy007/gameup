package com.gamesUP.gamesUP.dao;

import com.gamesUP.gamesUP.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    Optional<Purchase> findByClient_Id(Long clientId);
    Optional<Purchase> findByDelivered(boolean delivered);
}
