package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.GameRepository;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService{
    @Autowired
    GameRepository repository;
    public Game find(Long id){
        return  repository.findById( id).orElse(null);
    }
    public Game save(Game model){
        return  repository.saveAndFlush(model);
    }

    public List<Game> findAll() {
        return repository.findAll();
    }

    public Game update(Long id, Game model) {
        Optional<Game> existingGameOptional = repository.findById(id);
        if (existingGameOptional.isPresent()) {
            Game existingGame = existingGameOptional.get();
            BeanUtils.copyProperties(model, existingGame, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingGame);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
