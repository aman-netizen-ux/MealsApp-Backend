package com.meals.meals_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MealCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @NotEmpty(message = "Meal name is required")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Valid
    @NotEmpty(message = "Meals are required and can't be empty or null")
    private List<MealsInSpecificCategory> meals = new ArrayList<>();

    public void addMeal(MealsInSpecificCategory meal) {
        if (!meals.contains(meal)) {
            meals.add(meal);
        }
    }

}