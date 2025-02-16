package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController implements CRUDController<Purchase, Long> {

    private PurchaseService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Purchase>> getAll() {
        List<Purchase> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getOne(@PathVariable Long id) {
        Purchase model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                            : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Purchase> create(@RequestBody Purchase model) {
        Purchase saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable Long id, @RequestBody Purchase model) {
        Purchase updated = service.update(id, model);
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
