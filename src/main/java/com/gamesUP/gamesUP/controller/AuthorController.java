package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController implements CRUDController<Author, Long> {

    private AuthorService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        List<Author> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Author> getOne(@PathVariable Long id) {
        Author model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author model) {
        Author saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author model) {
        Author updated = service.update(id, model);
        return updated != null ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
