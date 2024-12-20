package com.meals.meals_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealsInSpecificCategoryDTO {
    private Long id;
    private String mealName;
    private String level;
    private String timeTaken;
    private String affordability;

    private Boolean isGlutenFree;
    private Boolean isVegan;
    private Boolean isVegetarian;
    private Boolean isLactoseFree;

    private MealDetailsDTO mealDetails;
}
