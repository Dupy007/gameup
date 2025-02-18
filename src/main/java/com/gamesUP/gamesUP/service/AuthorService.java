package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.AuthorRepository;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.utils.Functions;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository repository;

    public Author find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<Author> findAuthorByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Author save(Author model) {
        return repository.saveAndFlush(model);
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Author update(Long id, Author model) {
        Optional<Author> existingAuthorOptional = repository.findById(id);
        if (existingAuthorOptional.isPresent()) {
            Author existingAuthor = existingAuthorOptional.get();
            BeanUtils.copyProperties(model, existingAuthor, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingAuthor);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
