package com.meals.meals_app.service;

import com.meals.meals_app.dto.MealBookmarkDTO;
import com.meals.meals_app.dto.MealDetailsDTO;
import com.meals.meals_app.entity.MealDetails;
import com.meals.meals_app.exceptions.ResourceNotFoundException;
import com.meals.meals_app.repository.MealDetailsRepository;
import com.meals.meals_app.repository.MealsInSpecificCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealDetailsService {

    @Autowired
    private MealDetailsRepository mealDetailsRepository;

    @Autowired
    private MealsInSpecificCategoryRepository mealsInSpecificCategoryRepository;

    public boolean addMealInFavourite(Long mealId, MealBookmarkDTO mealBookmarkDTO) {
        MealDetails mealDetails = mealDetailsRepository.findMealDetailsById(mealId);

        if (mealDetails != null) {
            mealDetails.setIsFavorite(mealBookmarkDTO.getIsFavorite()); // Update the 'isFavorite' field
            mealsInSpecificCategoryRepository.save(mealDetails.getMeal()); // Save the entire meal entity, including details
            return true;
        } else {
            throw new ResourceNotFoundException("Meal details not found for meal id: " + mealId);
        }
    }

    public List<MealDetailsDTO> getFavoriteMeals() {
        List<MealDetails> allMeals = mealDetailsRepository.findAll();


        return allMeals.stream().filter(MealDetails::getIsFavorite)
                .map(meal -> {
                    return new MealDetailsDTO(meal.getId(), meal.getMealName(), meal.getIngredients(), true, meal.getSteps());
                }).collect(Collectors.toList());
    }
}
