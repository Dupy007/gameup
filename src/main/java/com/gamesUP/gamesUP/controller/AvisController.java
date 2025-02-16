package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avis")
@AllArgsConstructor
public class AvisController implements CRUDController<Avis, Long> {

    private AvisService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Avis>> getAll() {
        List<Avis> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Avis> getOne(@PathVariable Long id) {
        Avis model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                            : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Avis> create(@RequestBody Avis model) {
        Avis saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Avis> update(@PathVariable Long id, @RequestBody Avis model) {
        Avis updated = service.update(id, model);
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
