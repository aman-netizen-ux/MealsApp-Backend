package com.meals.meals_app.service;

import com.meals.meals_app.dto.FavoriteMealsDTO;
import com.meals.meals_app.entity.FavoriteMeals;
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
public class FavoriteMealsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealsInSpecificCategoryRepository mealsInSpecificCategoryRepository;

    @Autowired
    private FavoriteMealsRepository favoriteMealsRepository;

    @Autowired
    private MealsInSpecificCategoryService mealsInSpecificCategoryService;

    public boolean addFavoriteMeal(Long userId, Long mealId){
        // Find the user with the userId
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        // Find the respective meal with the mealId
        MealsInSpecificCategory meal = mealsInSpecificCategoryRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + mealId));

        FavoriteMeals favoriteMeals =new FavoriteMeals();
        favoriteMeals.setUser(user);
        favoriteMeals.setMeal(meal);

        favoriteMealsRepository.save(favoriteMeals);
        return true;
//        return new FavoriteMealsDTO(favoriteMeals.getMeal().getId(), userId, mealId, favoriteMeals.getMeal().getMealName());


    }

    public List<FavoriteMealsDTO> getFavoriteMeals(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return favoriteMealsRepository.findByUser(user).stream().map(favorite -> FavoriteMealsDTO.builder().mealId(favorite.getMeal().getId())
                .mealName(favorite.getMeal().getMealName())
                .userId(userId)
                .level(favorite.getMeal().getLevel())
                .affordability(favorite.getMeal().getAffordability())
                .timeTaken(favorite.getMeal().getTimeTaken())
                .id(favorite.getId())
                .build()).collect(Collectors.toList());

    }

}
