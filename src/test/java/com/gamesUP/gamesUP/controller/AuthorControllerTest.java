package com.gamesUP.gamesUP.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.security.JwtUtil;
import com.gamesUP.gamesUP.service.AuthorService;
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

@WebMvcTest(AuthorController.class)
@Import(JwtUtil.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService service;

    @Autowired
    private ObjectMapper objectMapper;

    // Injecter votre utilitaire JWT
    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        // Générer un token valide pour un utilisateur fictif (par exemple "testuser")
        token = jwtUtil.generateToken("testuser");
    }

    @Test
    @DisplayName("GET /author - Success with token")
    void testGetAllAuthors() throws Exception {
        // Préparer des données de test
        Author author1 = new Author();
        author1.setId(1L);
        author1.setPrenom("John");
        author1.setNom("Doe");
        author1.setEmail("john.doe@example.com");

        Author author2 = new Author();
        author2.setId(2L);
        author2.setPrenom("Jane");
        author2.setNom("Doe");
        author2.setEmail("jane.doe@example.com");

        List<Author> authors = Arrays.asList(author1, author2);
        Mockito.when(service.findAll()).thenReturn(authors);

        // Exécuter la requête GET en ajoutant le token dans l'en-tête Authorization
        mockMvc.perform(get("/author")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(author1.getId()))
                .andExpect(jsonPath("$[0].prenom").value(author1.getPrenom()))
                .andExpect(jsonPath("$[1].id").value(author2.getId()))
                .andExpect(jsonPath("$[1].email").value(author2.getEmail()));
    }

    // De la même manière, pour les autres endpoints, ajoutez le header "Authorization"
    @Test
    @DisplayName("GET /author/{id} - Found with token")
    void testGetAuthorByIdFound() throws Exception {
        Author author = new Author();
        author.setId(1L);
        author.setPrenom("John");
        author.setNom("Doe");
        author.setEmail("john.doe@example.com");

        Mockito.when(service.find(1L)).thenReturn(author);

        mockMvc.perform(get("/author/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.prenom").value(author.getPrenom()))
                .andExpect(jsonPath("$.email").value(author.getEmail()));
    }

    @Test
    @DisplayName("POST /author - Create Author with token")
    void testCreateAuthor() throws Exception {
        Author authorToCreate = new Author();
        authorToCreate.setPrenom("Alice");
        authorToCreate.setNom("Smith");
        authorToCreate.setEmail("alice.smith@example.com");

        Author createdAuthor = new Author();
        createdAuthor.setId(1L);
        createdAuthor.setPrenom(authorToCreate.getPrenom());
        createdAuthor.setNom(authorToCreate.getNom());
        createdAuthor.setEmail(authorToCreate.getEmail());

        Mockito.when(service.save(any(Author.class))).thenReturn(createdAuthor);

        mockMvc.perform(post("/author")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdAuthor.getId()))
                .andExpect(jsonPath("$.prenom").value(createdAuthor.getPrenom()));
    }

    @Test
    @DisplayName("PUT /author/{id} - Update Author with token")
    void testUpdateAuthor() throws Exception {
        Author updatedData = new Author();
        updatedData.setPrenom("AliceUpdated");
        updatedData.setNom("SmithUpdated");
        updatedData.setEmail("alice.updated@example.com");

        Author updatedAuthor = new Author();
        updatedAuthor.setId(1L);
        updatedAuthor.setPrenom(updatedData.getPrenom());
        updatedAuthor.setNom(updatedData.getNom());
        updatedAuthor.setEmail(updatedData.getEmail());

        Mockito.when(service.update(eq(1L), any(Author.class))).thenReturn(updatedAuthor);

        mockMvc.perform(put("/author/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedAuthor.getId()))
                .andExpect(jsonPath("$.prenom").value(updatedAuthor.getPrenom()))
                .andExpect(jsonPath("$.email").value(updatedAuthor.getEmail()));
    }

    @Test
    @DisplayName("DELETE /author/{id} - Delete Author with token")
    void testDeleteAuthor() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/author/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
