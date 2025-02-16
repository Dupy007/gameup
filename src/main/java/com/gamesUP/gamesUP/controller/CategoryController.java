package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController implements CRUDController<Category, Long> {

    private CategoryService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id) {
        Category model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                            : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category model) {
        Category saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category model) {
        Category updated = service.update(id, model);
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
