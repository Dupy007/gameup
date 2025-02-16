package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.PurchaseRepository;
import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository repository;
    public Purchase find(Long id){
        return  repository.findById( id).orElse(null);
    }
    public Purchase save(Purchase model){
        return  repository.saveAndFlush(model);
    }

    public List<Purchase> findAll() {
        return repository.findAll();
    }

    public Purchase update(Long id, Purchase model) {
        Optional<Purchase> existingPurchaseOptional = repository.findById(id);
        if (existingPurchaseOptional.isPresent()) {
            Purchase existingPurchase = existingPurchaseOptional.get();
            BeanUtils.copyProperties(model, existingPurchase, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingPurchase);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
