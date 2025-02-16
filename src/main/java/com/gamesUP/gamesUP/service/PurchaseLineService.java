package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.PurchaseLineRepository;
import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseLineService {
    @Autowired
    private PurchaseLineRepository repository;

    public PurchaseLine find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PurchaseLine save(PurchaseLine model) {
        return repository.saveAndFlush(model);
    }

    public List<PurchaseLine> findAll() {
        return repository.findAll();
    }

    public PurchaseLine update(Long id, PurchaseLine model) {
        Optional<PurchaseLine> existingPurchaseLineOptional = repository.findById(id);
        if (existingPurchaseLineOptional.isPresent()) {
            PurchaseLine existingPurchaseLine = existingPurchaseLineOptional.get();
            BeanUtils.copyProperties(model, existingPurchaseLine, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingPurchaseLine);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
