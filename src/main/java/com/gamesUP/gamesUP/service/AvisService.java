package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.AvisRepository;
import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisService {
    @Autowired
    private AvisRepository repository;

    public Avis find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Avis save(Avis model) {
        return repository.saveAndFlush(model);
    }

    public List<Avis> findAll() {
        return repository.findAll();
    }

    public Avis update(Long id, Avis model) {
        Optional<Avis> existingAvisOptional = repository.findById(id);
        if (existingAvisOptional.isPresent()) {
            Avis existingAvis = existingAvisOptional.get();
            BeanUtils.copyProperties(model, existingAvis, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingAvis);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
