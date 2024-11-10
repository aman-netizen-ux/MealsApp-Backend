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
import java.util.Optional;
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

    public boolean addFavoriteMeal(String userId, Long mealId) {
        // Find the user with the userId
        User user = userRepository.findById(userId).orElseGet(() -> {
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setIsGlutenFree(false);
            newUser.setIsVegan(false);
            newUser.setIsVegetarian(false);
            newUser.setIsLactoseFree(false);
            return userRepository.save(newUser);
        });
        // Find the respective meal with the mealId
        MealsInSpecificCategory meal = mealsInSpecificCategoryRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + mealId));

        FavoriteMeals favoriteMeals = new FavoriteMeals();
        favoriteMeals.setUser(user);
        favoriteMeals.setMeal(meal);

        favoriteMealsRepository.save(favoriteMeals);
        return true;

    }

    public boolean removeFavoriteMeal(String userId, Long mealId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with the id: " + userId));
        MealsInSpecificCategory meal = mealsInSpecificCategoryRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + mealId));

        Optional<FavoriteMeals> alreadyFavorite = Optional.ofNullable(favoriteMealsRepository.findByUserAndMeal(user, meal));

        if(alreadyFavorite.isPresent()){
            //If the meal is present, then remove from the favorites
            favoriteMealsRepository.delete(alreadyFavorite.get());
            return true;
        }
        return false;
    }

    public List<FavoriteMealsDTO> getFavoriteMeals(String userId) {
        User user = userRepository.findById(userId).orElseGet(() -> {
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setIsGlutenFree(false);
            newUser.setIsVegan(false);
            newUser.setIsVegetarian(false);
            newUser.setIsLactoseFree(false);
            return userRepository.save(newUser);
        });

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
