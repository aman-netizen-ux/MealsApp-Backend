package com.meals.meals_app.controller;

import com.meals.meals_app.dto.MealBookmarkDTO;
import com.meals.meals_app.dto.MealDetailsDTO;
import com.meals.meals_app.dto.ResponseMessage;
import com.meals.meals_app.service.MealDetailsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class MealDetailsController {

    @Autowired
    private MealDetailsService mealDetailsService;


    @PutMapping("/bookmarkMeal/{mealId}")
    public ResponseEntity<ResponseMessage> bookmarkMeal(@PathVariable Long mealId, @RequestBody @Valid MealBookmarkDTO mealBookmarkDTO) {
        boolean bookmarkAdded = mealDetailsService.addMealInFavourite(mealId, mealBookmarkDTO);

        if (bookmarkAdded) {
            return ResponseEntity.ok(new ResponseMessage("Meal bookmarked successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Unable to bookmark the meal"));
        }
    }

    @GetMapping("/getFavoriteMeals")
    public ResponseEntity<List<MealDetailsDTO>> favoriteMeals(){
        List<MealDetailsDTO> favorites = mealDetailsService.getFavoriteMeals();
        return ResponseEntity.ok(favorites);
    }
}
