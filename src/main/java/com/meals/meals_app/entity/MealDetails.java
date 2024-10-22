package com.meals.meals_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> ingredients;
    private String mealName;
    private Boolean isFavorite;
    private List<String> steps;

    @OneToOne
    @JoinColumn(name = "meal_id", nullable = false)
    @ToString.Exclude
    private MealsInSpecificCategory meal;


}
