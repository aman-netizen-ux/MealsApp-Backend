package com.meals.meals_app.repository;

import com.meals.meals_app.dto.MealDetailsDTO;
import com.meals.meals_app.entity.MealDetails;
import com.meals.meals_app.entity.MealsInSpecificCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealsInSpecificCategoryRepository extends JpaRepository<MealsInSpecificCategory, Long> {
    List<MealsInSpecificCategory> findByCategory_Id(Long mealCategoryId);
}
