package com.meals.meals_app.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealsInSpecificCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @NotEmpty(message = "Meal name is required")
    private String mealName;
    @Valid
    @NotEmpty(message = "Level is required")
    private String level;
    @Valid
    @NotEmpty(message = "Time Taken is required")
    private String timeTaken;
    @Valid
    @NotEmpty(message = "Affordability is required")
    private String affordability;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private MealCategory category;

    @Valid
    @NotNull(message = "isGlutenFree is required")
    private Boolean isGlutenFree;
    @Valid
    @NotNull(message = "isVegan is required")
    private Boolean isVegan;
    @Valid
    @NotNull(message = "isVegetarian is required")
    private Boolean isVegetarian;
    @Valid
    @NotNull(message = "isLactoseFree is required")
    private Boolean isLactoseFree;

    @OneToOne(mappedBy = "meal", cascade = CascadeType.ALL)
    private MealDetails mealDetails;

}
