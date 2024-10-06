package com.meals.meals_app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealBookmarkDTO {
    @Valid
    @NotNull(message = "Bookmark value can't be null or empty")
    private Boolean isFavorite;
}
