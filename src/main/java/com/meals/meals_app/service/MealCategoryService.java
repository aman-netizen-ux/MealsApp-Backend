package com.meals.meals_app.service;

import com.meals.meals_app.dto.MealCategoryDTO;
import com.meals.meals_app.entity.MealCategory;
import com.meals.meals_app.repository.MealCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealCategoryService {

    @Autowired
    private MealCategoryRepository mealCategoryRepository;

    public List<MealCategoryDTO> getAllCategories() {
        List<MealCategory> categories = mealCategoryRepository.findAll();
        return categories.stream().map(category -> {
//            List<MealsInSpecificCategoryDTO> meals = category.getMeals().stream()
//                    .map(meal -> {
//                        MealDetailsDTO mealDetailsDTO = null;
//                        if (meal.getMealDetails() != null) {
//                            mealDetailsDTO = new MealDetailsDTO(
//                                    meal.getMealDetails().getId(),
//                                    meal.getMealDetails().getMealName(),
//                                    meal.getMealDetails().getIngredients(),
//                                    meal.getMealDetails().getIsFavorite(),
//                                    meal.getMealDetails().getSteps()
//                            );
//                        }
//                        return new MealsInSpecificCategoryDTO(
//                                meal.getId(),
//                                meal.getMealName(),
//                                meal.getLevel(),
//                                meal.getAffordability(),
//                                meal.getTimeTaken(),
//                                mealDetailsDTO
//                        );
//                    }).collect(Collectors.toList());
//            return new MealCategoryDTO(category.getId(), category.getName(), meals);
            return new MealCategoryDTO(category.getId(), category.getName());
        }).collect(Collectors.toList());
    }

//    public List<CategoryBasedMealsDTO> getCategorySpecificMeal() {
//        List<MealCategory> categories = mealCategoryRepository.findAll();
//        return categories.stream().map(category -> {
//            List<MealsInSpecificCategoryDTO> meals = category.getMeals().stream()
//                    .map(meal -> {
//                        MealDetailsDTO mealDetailsDTO = null;
//                        if (meal.getMealDetails() != null) {
//                            mealDetailsDTO = new MealDetailsDTO(
//                                    meal.getMealDetails().getId(),
//                                    meal.getMealDetails().getMealName(),
//                                    meal.getMealDetails().getIngredients(),
//                                    meal.getMealDetails().getIsFavorite(),
//                                    meal.getMealDetails().getSteps()
//                            );
//                        }
//                        return new MealsInSpecificCategoryDTO(
//                                meal.getId(),
//                                meal.getMealName(),
//                                meal.getLevel(),
//                                meal.getAffordability(),
//                                meal.getTimeTaken(),
//                                mealDetailsDTO
//                        );
//                    }).collect(Collectors.toList());
//            return new CategoryBasedMealsDTO(category.getId(), category.getName(), meals);
////            return new MealCategoryDTO(category.getId(), category.getName());
//        }).collect(Collectors.toList());
//    }


    @Transactional
    public boolean addMealCategory(MealCategory category) {
        MealCategory savedCategory = mealCategoryRepository.save(category);
        return savedCategory.getId() != null;
    }


}
