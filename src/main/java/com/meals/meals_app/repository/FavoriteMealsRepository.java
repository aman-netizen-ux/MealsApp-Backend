package com.meals.meals_app.repository;

import com.meals.meals_app.entity.FavoriteMeals;
import com.meals.meals_app.entity.MealsInSpecificCategory;
import com.meals.meals_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteMealsRepository extends JpaRepository<FavoriteMeals, Long>{
    boolean existsByUserAndMeal(User user, MealsInSpecificCategory meal);
    List<FavoriteMeals> findByUser(User user);
    FavoriteMeals findByUserAndMeal(User user, MealsInSpecificCategory meal);
}
