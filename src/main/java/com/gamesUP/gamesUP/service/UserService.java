package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.UserRepository;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public User find(Long id){
        return  repository.findById( id).orElse(null);
    }
     public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
    public User save(User model){
        return  repository.saveAndFlush(model);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User update(Long id, User model) {
        Optional<User> existingUserOptional = repository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            BeanUtils.copyProperties(model, existingUser, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingUser);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
