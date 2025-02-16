package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.CategoryRepository;
import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    public Category find(Long id){
        return  repository.findById( id).orElse(null);
    }
    public Category save(Category model){
        return  repository.saveAndFlush(model);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category update(Long id, Category model) {
        Optional<Category> existingCategoryOptional = repository.findById(id);
        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            BeanUtils.copyProperties(model, existingCategory, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingCategory);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
