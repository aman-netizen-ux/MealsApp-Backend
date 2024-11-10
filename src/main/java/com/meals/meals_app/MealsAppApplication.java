package com.meals.meals_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.meals.meals_app.config.FirebaseConfig;

@SpringBootApplication
public class MealsAppApplication {

	public static void main(String[] args) {
		FirebaseConfig.initializeFirebase();
		System.out.println("firebase initialized successfully");
		SpringApplication.run(MealsAppApplication.class, args);
	}

}
