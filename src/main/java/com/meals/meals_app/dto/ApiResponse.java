package com.meals.meals_app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

}

