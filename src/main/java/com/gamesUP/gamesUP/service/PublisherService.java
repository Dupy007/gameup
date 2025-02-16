package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.PublisherRepository;
import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    PublisherRepository repository;
    public Publisher find(Long id){
        return  repository.findById( id).orElse(null);
    }
    public Publisher save(Publisher model){
        return  repository.saveAndFlush(model);
    }

    public List<Publisher> findAll() {
        return repository.findAll();
    }

    public Publisher update(Long id, Publisher model) {
        Optional<Publisher> existingPublisherOptional = repository.findById(id);
        if (existingPublisherOptional.isPresent()) {
            Publisher existingPublisher = existingPublisherOptional.get();
            BeanUtils.copyProperties(model, existingPublisher, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingPublisher);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
