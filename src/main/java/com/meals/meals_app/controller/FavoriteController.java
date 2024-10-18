package com.meals.meals_app.controller;

import com.meals.meals_app.dto.FavoriteMealsDTO;
import com.meals.meals_app.service.FavoriteMealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteMealsService favoriteService;

    // Mark a meal as favorite
    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestParam Long mealId, @RequestParam Long userId) {
        boolean added = favoriteService.addFavoriteMeal(userId, mealId);

        if (added) {
            return ResponseEntity.ok("Meal marked as favorite");
        } else {
            return ResponseEntity.status(500).body("Failed to mark meal as favorite");
        }
    }

    // Get all favorite meals for a user
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteMealsDTO>> getFavorites(@PathVariable Long userId) {
        List<FavoriteMealsDTO> favoriteMeals = favoriteService.getFavoriteMeals(userId);
        return ResponseEntity.ok(favoriteMeals);
    }
}
