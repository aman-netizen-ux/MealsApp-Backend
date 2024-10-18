package com.meals.meals_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Boolean isGlutenFree;
    private Boolean isVegan;
    private Boolean isVegetarian;
    private Boolean isLactoseFree;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  List<FavoriteMeals> favoriteMeals;
}
