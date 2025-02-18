package com.gamesUP.gamesUP.service;

import com.gamesUP.gamesUP.dao.WishlistRepository;
import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.utils.Functions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository repository;

    public Wishlist find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Wishlist save(Wishlist model) {
        return repository.saveAndFlush(model);
    }

    public List<Wishlist> findAll() {
        return repository.findAll();
    }

    public Wishlist update(Long id, Wishlist model) {
        Optional<Wishlist> existingWishlistOptional = repository.findById(id);
        if (existingWishlistOptional.isPresent()) {
            Wishlist existingWishlist = existingWishlistOptional.get();
            BeanUtils.copyProperties(model, existingWishlist, Functions.getNullPropertyNames(model));
            return repository.saveAndFlush(existingWishlist);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
