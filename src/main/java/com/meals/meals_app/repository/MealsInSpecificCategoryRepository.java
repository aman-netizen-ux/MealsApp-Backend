package com.meals.meals_app.repository;

import com.meals.meals_app.entity.MealsInSpecificCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface MealsInSpecificCategoryRepository extends JpaRepository<MealsInSpecificCategory, Long> {
    List<MealsInSpecificCategory> findByCategory_Id(Long mealCategoryId);


    @Query("SELECT m FROM MealsInSpecificCategory m " +
            "WHERE m.category.id = :categoryId " +
            "AND (:isVegetarian = false OR m.isVegetarian = true) " +
            "AND (:isVegan = false OR m.isVegan = true) " +
            "AND (:isLactoseFree = false OR m.isLactoseFree = true) " +
            "AND (:isGlutenFree = false OR m.isGlutenFree = true)"
    )
    List<MealsInSpecificCategory> findAllByCategoryAndFilters(
            @Param("categoryId") Long categoryId,
            @Param("isVegetarian") boolean isVegetarian,
            @Param("isVegan") boolean isVegan,
            @Param("isLactoseFree") boolean isLactoseFree,
            @Param("isGlutenFree") boolean isGlutenFree
    );

}
