package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Client;
import com.gamesUP.gamesUP.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController implements CRUDController<Client, Long> {

    private ClientService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable Long id) {
        Client model = service.find(id);
        return model != null ? ResponseEntity.ok(model)
                            : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client model) {
        Client saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client model) {
        Client updated = service.update(id, model);
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
