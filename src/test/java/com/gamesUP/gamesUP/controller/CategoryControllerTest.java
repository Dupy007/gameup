package com.gamesUP.gamesUP.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.security.JwtUtil;
import com.gamesUP.gamesUP.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
@Import(JwtUtil.class)
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        // Générer un token pour l'utilisateur "testuser"
        token = jwtUtil.generateToken("testuser");
    }

    @Test
    @DisplayName("GET /category - Success with token")
    void testGetAllCategories() throws Exception {
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setType("Action");

        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setType("Adventure");

        List<Category> categories = Arrays.asList(cat1, cat2);
        Mockito.when(service.findAll()).thenReturn(categories);

        mockMvc.perform(get("/category")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(cat1.getId()))
                .andExpect(jsonPath("$[0].type").value(cat1.getType()))
                .andExpect(jsonPath("$[1].id").value(cat2.getId()))
                .andExpect(jsonPath("$[1].type").value(cat2.getType()));
    }

    @Test
    @DisplayName("GET /category/{id} - Found with token")
    void testGetCategoryByIdFound() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setType("Action");

        Mockito.when(service.find(1L)).thenReturn(category);

        mockMvc.perform(get("/category/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(category.getId()))
                .andExpect(jsonPath("$.type").value(category.getType()));
    }

    @Test
    @DisplayName("GET /category/{id} - Not Found with token")
    void testGetCategoryByIdNotFound() throws Exception {
        Mockito.when(service.find(1L)).thenReturn(null);

        mockMvc.perform(get("/category/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /category - Create Category with token")
    void testCreateCategory() throws Exception {
        Category newCategory = new Category();
        newCategory.setType("New Category");

        Category savedCategory = new Category();
        savedCategory.setId(1L);
        savedCategory.setType(newCategory.getType());

        Mockito.when(service.save(any(Category.class))).thenReturn(savedCategory);

        mockMvc.perform(post("/category")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCategory.getId()))
                .andExpect(jsonPath("$.type").value(savedCategory.getType()));
    }

    @Test
    @DisplayName("PUT /category/{id} - Update Category with token")
    void testUpdateCategory() throws Exception {
        Category updatedData = new Category();
        updatedData.setType("Updated Category");

        Category updatedCategory = new Category();
        updatedCategory.setId(1L);
        updatedCategory.setType(updatedData.getType());

        Mockito.when(service.update(eq(1L), any(Category.class))).thenReturn(updatedCategory);

        mockMvc.perform(put("/category/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedCategory.getId()))
                .andExpect(jsonPath("$.type").value(updatedCategory.getType()));
    }

    @Test
    @DisplayName("DELETE /category/{id} - Delete Category with token")
    void testDeleteCategory() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/category/{id}", 1L)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
