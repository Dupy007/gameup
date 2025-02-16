package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Login;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.security.JwtUtil;
import com.gamesUP.gamesUP.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private AuthService service;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity Login(@Valid @RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println(login);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getMotdepasse())
            );
            User user = service.login(login, request, response);
            if (user != null) {
                // Si l'authentification réussit, générer un token
                String token = jwtUtil.generateToken(user.getUsername());
                response.addHeader("Authorization", "Bearer " + token);
                return ResponseEntity.ok(user);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user, HttpServletResponse response) throws URISyntaxException {
        try {
            User newuser = service.register(user);
            if (newuser != null) {
                String token = jwtUtil.generateToken(user.getUsername());
                response.addHeader("Authorization", "Bearer " + token);
                return ResponseEntity.created(new URI("users/"+user.getId())).body(user);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(e.getMessage());
        }
    }
}
