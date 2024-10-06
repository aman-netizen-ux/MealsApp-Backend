package com.meals.meals_app.repository;

import com.meals.meals_app.entity.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealCategoryRepository extends JpaRepository<MealCategory, Long> {

}