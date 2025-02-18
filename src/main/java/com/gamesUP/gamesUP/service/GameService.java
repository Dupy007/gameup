package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.GameRepository;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.utils.Functions;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository repository;

    public Game find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Game save(Game model) {
        return repository.saveAndFlush(model);
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

    // 🔍 Recherche avancée
    public List<Game> searchGames(String gameName, String authorName, String publisherName) {
        Specification<Game> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (gameName != null && !gameName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nom")), "%" + gameName.toLowerCase() + "%"));
            }

            if (authorName != null && !authorName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author").get("nom")), "%" + authorName.toLowerCase() + "%"));
            }

            if (publisherName != null && !publisherName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("publisher").get("name")), "%" + publisherName.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(spec);
    }
}
