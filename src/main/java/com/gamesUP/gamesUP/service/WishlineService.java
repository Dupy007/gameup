package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.WishlineRepository;
import com.gamesUP.gamesUP.model.WishLine;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlineService {
    @Autowired
    private WishlineRepository repository;
    public WishLine find(Long id){
        return  repository.findById( id).orElse(null);
    }
    public WishLine save(WishLine model){
        return  repository.saveAndFlush(model);
    }

    public List<WishLine> findAll() {
        return repository.findAll();
    }

    public WishLine update(Long id, WishLine model) {
        Optional<WishLine> existingWishLineOptional = repository.findById(id);
        if (existingWishLineOptional.isPresent()) {
            WishLine existingWishLine = existingWishLineOptional.get();
            BeanUtils.copyProperties(model, existingWishLine, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingWishLine);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
