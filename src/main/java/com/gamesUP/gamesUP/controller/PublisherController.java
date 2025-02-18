package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@AllArgsConstructor
public class PublisherController implements CRUDController<Publisher, Long> {

    private PublisherService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Publisher>> getAll() {
        List<Publisher> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getOne(@PathVariable Long id) {
        Publisher model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody Publisher model) {
        Publisher saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@PathVariable Long id, @RequestBody Publisher model) {
        Publisher updated = service.update(id, model);
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
