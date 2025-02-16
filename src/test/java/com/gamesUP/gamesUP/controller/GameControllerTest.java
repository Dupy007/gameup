package com.gamesUP.gamesUP.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.security.JwtUtil;
import com.gamesUP.gamesUP.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
@Import(JwtUtil.class)
@AutoConfigureMockMvc(addFilters = false)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        // Génère un token pour "testuser"
        token = jwtUtil.generateToken("testuser");
    }

    @Test
    @DisplayName("GET /games - Success with token")
    void testGetAllGames() throws Exception {
        Game game1 = new Game();
        game1.setId(1L);
        game1.setNom("Game One");
        // Vous pouvez ajouter d'autres propriétés si besoin

        Game game2 = new Game();
        game2.setId(2L);
        game2.setNom("Game Two");

        List<Game> games = Arrays.asList(game1, game2);
        Mockito.when(service.findAll()).thenReturn(games);

        mockMvc.perform(get("/games")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(game1.getId()))
                .andExpect(jsonPath("$[0].nom").value(game1.getNom()))
                .andExpect(jsonPath("$[1].id").value(game2.getId()))
                .andExpect(jsonPath("$[1].nom").value(game2.getNom()));
    }

    @Test
    @DisplayName("GET /games/{id} - Found with token")
    void testGetGameByIdFound() throws Exception {
        Game game = new Game();
        game.setId(1L);
        game.setNom("Game One");

        Mockito.when(service.find(1L)).thenReturn(game);

        mockMvc.perform(get("/games/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(game.getId()))
                .andExpect(jsonPath("$.nom").value(game.getNom()));
    }

    @Test
    @DisplayName("GET /games/{id} - Not Found with token")
    void testGetGameByIdNotFound() throws Exception {
        Mockito.when(service.find(1L)).thenReturn(null);

        mockMvc.perform(get("/games/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /games - Create Game with token")
    void testCreateGame() throws Exception {
        Game newGame = new Game();
        newGame.setNom("New Game");
        // Ajoutez d'autres propriétés si nécessaire

        Game savedGame = new Game();
        savedGame.setId(1L);
        savedGame.setNom(newGame.getNom());

        Mockito.when(service.save(any(Game.class))).thenReturn(savedGame);

        mockMvc.perform(post("/games")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newGame)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedGame.getId()))
                .andExpect(jsonPath("$.nom").value(savedGame.getNom()));
    }

    @Test
    @DisplayName("PUT /games/{id} - Update Game with token")
    void testUpdateGame() throws Exception {
        Game updateData = new Game();
        updateData.setNom("Updated Game");
        // Ajoutez d'autres propriétés si nécessaire

        Game updatedGame = new Game();
        updatedGame.setId(1L);
        updatedGame.setNom(updateData.getNom());

        Mockito.when(service.update(eq(1L), any(Game.class))).thenReturn(updatedGame);

        mockMvc.perform(put("/games/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedGame.getId()))
                .andExpect(jsonPath("$.nom").value(updatedGame.getNom()));
    }

    @Test
    @DisplayName("DELETE /games/{id} - Delete Game with token")
    void testDeleteGame() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/games/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
