package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController implements CRUDController<User, Long> {

    private UserService service;

    @Override
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Long id) {
        User model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                            : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User model) {
        User saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User model) {
        User updated = service.update(id, model);
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
