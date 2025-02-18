package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@AllArgsConstructor
public class WhistlistController implements CRUDController<Wishlist, Long> {

    private WishlistService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Wishlist>> getAll() {
        List<Wishlist> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getOne(@PathVariable Long id) {
        Wishlist model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Wishlist> create(@RequestBody Wishlist model) {
        Wishlist saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> update(@PathVariable Long id, @RequestBody Wishlist model) {
        Wishlist updated = service.update(id, model);
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
