package com.meals.meals_app.controller;

import com.meals.meals_app.dto.ApiResponse;
import com.meals.meals_app.dto.MealCategoryDTO;
import com.meals.meals_app.entity.MealCategory;
import com.meals.meals_app.service.MealCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import shaded_package.io.swagger.models.apideclaration.ResponseMessage;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/public")
public class MealCategoryController {

    @Autowired
    private MealCategoryService mealCategoryService;

    //Get All Categories
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<MealCategoryDTO>> getAllMealCategories() {
        List<MealCategoryDTO> categories = mealCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //Add a category and meal associated with it - FOR ADMIN ONLY
    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addMealCategory(@Valid @RequestBody MealCategory category) {

        category.getMeals().forEach(meal -> {
            meal.setCategory(category);
            category.addMeal(meal);
            if (meal.getMealDetails() != null) {
                meal.getMealDetails().setMeal(meal);
            }
        });

        boolean isAdded = mealCategoryService.addMealCategory(category);
        if (isAdded) {
            return ResponseEntity.ok(new ApiResponse("Meal category and associated meals added successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to add category"));
        }
    }
}
