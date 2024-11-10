package com.meals.meals_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MealDetailsDTO {
    private Long id;
    private String mealName;
    private List<String> ingredients;
    private Boolean isFavorite;
    private List<String> steps;

}
