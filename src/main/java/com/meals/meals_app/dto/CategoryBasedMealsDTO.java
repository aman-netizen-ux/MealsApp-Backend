package com.meals.meals_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryBasedMealsDTO {
    private long id;
    private String name;
    private List<MealsInSpecificCategoryDTO> meals;

}
