package com.meals.meals_app.controller;

import com.meals.meals_app.dto.ApiResponse;
import com.meals.meals_app.dto.FavoriteMealsDTO;
import com.meals.meals_app.service.FavoriteMealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteMealsService favoriteService;

    // Mark a meal as favorite
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFavorite(HttpServletRequest request, @RequestParam Long mealId) {
        String userId = (String) request.getAttribute("userId");
        boolean added = favoriteService.addFavoriteMeal(userId, mealId);

        if (added) {
            return ResponseEntity.ok(new ApiResponse("Meal marked as favorite"));
        } else {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to mark meal as favorite"));
        }
    }

    // Get all favorite meals for a user
    @GetMapping("/getUserFavorites")
    public ResponseEntity<List<FavoriteMealsDTO>> getFavorites(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        List<FavoriteMealsDTO> favoriteMeals = favoriteService.getFavoriteMeals(userId);
        return ResponseEntity.ok(favoriteMeals);
    }

    //Remove favorite meal
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> removeFavorite(HttpServletRequest request,  @RequestParam Long mealId){
        String userId = (String)  request.getAttribute("userId");
        boolean removed = favoriteService.removeFavoriteMeal(userId, mealId);
        if(removed){
            return ResponseEntity.ok(new ApiResponse("Meal removed from favorites"));
        }else{
            return ResponseEntity.status(500).body(new ApiResponse("Failed to remove from favorites"));
        }
    }
}
