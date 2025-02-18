package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController implements CRUDController<Game, Long> {


    private GameService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        List<Game> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Game> getOne(@PathVariable Long id) {
        Game model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game model) {
        Game saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game model) {
        Game updated = service.update(id, model);
        return updated != null ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔍 Recherche avancée
    @PostMapping("/search")
    public ResponseEntity<List<Game>> searchGames(@RequestBody Map<String, String> searchParams) {
        String gameName = searchParams.get("game_name");
        String authorName = searchParams.get("author_name");
        String publisherName = searchParams.get("publisher_name");

        List<Game> results = service.searchGames(gameName, authorName, publisherName);
        return ResponseEntity.ok(results);
    }

}