package com.meals.meals_app.service;

import com.meals.meals_app.dto.MealDetailsDTO;
import com.meals.meals_app.dto.MealsInSpecificCategoryDTO;
import com.meals.meals_app.entity.MealsInSpecificCategory;
import com.meals.meals_app.entity.User;
import com.meals.meals_app.exceptions.ResourceNotFoundException;
import com.meals.meals_app.repository.FavoriteMealsRepository;
import com.meals.meals_app.repository.MealsInSpecificCategoryRepository;
import com.meals.meals_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealsInSpecificCategoryService {

    @Autowired
    private MealsInSpecificCategoryRepository mealsInSpecificCategoryRepository;

    @Autowired
    private FavoriteMealsRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    public MealsInSpecificCategoryDTO getSpecificMeal(Long mealId, String userId) {
        MealsInSpecificCategory meal = mealsInSpecificCategoryRepository.findById(mealId).orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + mealId));

        User user = userRepository.findById(userId).orElseGet(() -> {
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setIsGlutenFree(false);
            newUser.setIsVegan(false);
            newUser.setIsVegetarian(false);
            newUser.setIsLactoseFree(false);
            return userRepository.save(newUser);
        });

        boolean isFavorite = favoriteRepository.existsByUserAndMeal(user, meal.getMealDetails().getMeal());

        MealDetailsDTO mealDetailsDTO = getMealDetailsDTO(meal, isFavorite);
        return new MealsInSpecificCategoryDTO(
                meal.getId(),
                meal.getMealName(),
                meal.getLevel(),
                meal.getAffordability(),
                meal.getTimeTaken(),
                meal.getIsGlutenFree(),
                meal.getIsVegan(),
                meal.getIsVegetarian(),
                meal.getIsLactoseFree(),
                mealDetailsDTO
        );
    }

    //, Boolean isVegetarian, Boolean isVegan, Boolean isLactoseFree, Boolean isGlutenFree
    private static MealDetailsDTO getMealDetailsDTO(MealsInSpecificCategory meal, Boolean isFavorite) {
        MealDetailsDTO mealDetailsDTO = null;
        if (meal.getMealDetails() != null) {
            mealDetailsDTO = new MealDetailsDTO(
                    meal.getMealDetails().getId(),
                    meal.getMealDetails().getMealName(),
                    meal.getMealDetails().getIngredients(),
                    isFavorite,
                    meal.getMealDetails().getSteps()
            );
        }
        return mealDetailsDTO;
    }

    public List<MealsInSpecificCategoryDTO> getMealsOfSpecificCategory(Long mealCategoryId, Boolean isGlutenFree, Boolean isVegan, Boolean isVegetarian, Boolean isLactoseFree) {
        List<MealsInSpecificCategory> categoryMeals = mealsInSpecificCategoryRepository.findAllByCategoryAndFilters(
                mealCategoryId,
                isVegetarian,
                isVegan,
                isLactoseFree,
                isGlutenFree
        );

        // Directly return an empty list if no meals are found
        return categoryMeals.stream().map(categoryMeal -> {
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
                    categoryMeal.getIsGlutenFree(),
                    categoryMeal.getIsVegan(),
                    categoryMeal.getIsVegetarian(),
                    categoryMeal.getIsLactoseFree(),
                    mealDetailsDTO
            );
        }).collect(Collectors.toList());
    }

}