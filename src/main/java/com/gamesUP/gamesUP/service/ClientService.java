package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.ClientRepository;
import com.gamesUP.gamesUP.model.Client;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    public Client find(Long id){
        return  repository.findById( id).orElse(null);
    }
     public Optional<Client> findClientByEmail(String email) {
        return repository.findByEmail(email);
    }
    public Client save(Client model){
        return  repository.saveAndFlush(model);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client update(Long id, Client model) {
        Optional<Client> existingClientOptional = repository.findById(id);
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();
            BeanUtils.copyProperties(model, existingClient, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingClient);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
