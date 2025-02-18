package com.gamesUP.gamesUP.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDController<T, ID> {

    // Récupérer tous les éléments
    ResponseEntity<List<T>> getAll();

    // Récupérer un élément par son identifiant
    ResponseEntity<T> getOne(ID id);

    // Créer un nouvel élément
    ResponseEntity<T> create(T entity);

    // Mettre à jour un élément existant
    ResponseEntity<T> update(ID id, T entity);

    // Supprimer un élément
    ResponseEntity<Void> delete(ID id);
}