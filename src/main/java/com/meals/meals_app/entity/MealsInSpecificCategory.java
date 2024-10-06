package com.meals.meals_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private String mealName;
    private String level;
    private String timeTaken;
    private String affordability;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private MealCategory category;

    @OneToOne(mappedBy = "meal", cascade = CascadeType.ALL)
    private MealDetails mealDetails;

}
