package com.meals.meals_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.meals.meals_app.config.FirebaseConfig;

@SpringBootApplication
public class MealsAppApplication {

	public static void main(String[] args) {
		System.out.println("starting line");
		FirebaseConfig.initializeFirebase();
		System.out.println("firebase initialized successfully");
		String customToken = FirebaseTokenGenerator.createCustomToken("16ALczlzvnam9EYPEh3PzAKPVA22");
		System.out.println("Custom Token: " + customToken);
		SpringApplication.run(MealsAppApplication.class, args);
	}

}
