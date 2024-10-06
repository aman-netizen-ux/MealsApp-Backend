package com.meals.meals_app.controller;

import com.meals.meals_app.dto.MealsInSpecificCategoryDTO;
import com.meals.meals_app.service.MealsInSpecificCategoryService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class MealsInSpecificCategoryController {

    @Autowired
    private MealsInSpecificCategoryService mealsInSpecificCategoryService;


    @GetMapping("/getMealsInSpecificCategory/{mealCategoryId}")
    public ResponseEntity<List<MealsInSpecificCategoryDTO>> getAllMealsInSpecificCategory(@PathVariable() Long mealCategoryId) {
        List<MealsInSpecificCategoryDTO> meals = mealsInSpecificCategoryService.getMealsOfSpecificCategory(mealCategoryId);
        return ResponseEntity.ok(meals);
    }

}
