package com.meals.meals_app.service;

import com.meals.meals_app.dto.MealBookmarkDTO;
import com.meals.meals_app.dto.MealDetailsDTO;
import com.meals.meals_app.dto.MealsInSpecificCategoryDTO;
import com.meals.meals_app.entity.MealDetails;
import com.meals.meals_app.entity.MealsInSpecificCategory;
import com.meals.meals_app.exceptions.ResourceNotFoundException;
import com.meals.meals_app.repository.MealDetailsRepository;
import com.meals.meals_app.repository.MealsInSpecificCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealsInSpecificCategoryService {

    @Autowired
    private MealsInSpecificCategoryRepository mealsInSpecificCategoryRepository;


    public List<MealsInSpecificCategoryDTO> getMealsOfSpecificCategory(Long mealCategoryId) {
        List<MealsInSpecificCategory> categoryMeals = mealsInSpecificCategoryRepository.findByCategory_Id(mealCategoryId);

        if (categoryMeals.isEmpty()) {
            throw new ResourceNotFoundException("No meals found for category id: " + mealCategoryId);

        }
        return categoryMeals.stream().map(categoryMeal ->
                {
                    MealDetailsDTO mealDetailsDTO = null;
                    if (categoryMeal.getMealDetails() != null) {
                        mealDetailsDTO = new MealDetailsDTO(
                                categoryMeal.getMealDetails().getId(),
                                categoryMeal.getMealDetails().getMealName(),
                                categoryMeal.getMealDetails().getIngredients(),
                                categoryMeal.getMealDetails().getIsFavorite(),
                                categoryMeal.getMealDetails().getSteps()
                        );
                    }
                    return new MealsInSpecificCategoryDTO(
                            categoryMeal.getId(),
                            categoryMeal.getMealName(),
                            categoryMeal.getLevel(),
                            categoryMeal.getAffordability(),
                            categoryMeal.getTimeTaken(),
                            mealDetailsDTO
                    );
                })
                .collect(Collectors.toList());
    }
}