package com.meals.meals_app.repository;

import com.meals.meals_app.entity.MealDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealDetailsRepository extends JpaRepository<MealDetails, Long> {
}
