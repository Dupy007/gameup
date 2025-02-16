package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.UserRepository;
import com.gamesUP.gamesUP.model.Login;
import com.gamesUP.gamesUP.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("L'utilisateur avec l'email " + user.getEmail() + " existe déjà.");
        }
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Le username  " + user.getUsername() + " existe déjà.");
        }
        String encodedPassword = passwordEncoder.encode(user.getMotdepasse());
        user.setMotdepasse(encodedPassword);
        return repository.saveAndFlush(user);
    }


    public User login(Login login, HttpServletRequest request, HttpServletResponse response) {
        Optional<User> optionalUser = repository.findByEmail(login.getEmail());
        if (optionalUser.isPresent()) {
            boolean isValid = passwordEncoder.matches(login.getMotdepasse(), optionalUser.get().getMotdepasse());
            if (isValid) {
                return optionalUser.get();
            }
        }
        return optionalUser.orElse(null);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }
}
