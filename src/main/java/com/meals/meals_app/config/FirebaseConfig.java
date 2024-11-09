package com.meals.meals_app.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;


@Configuration
public class FirebaseConfig {
    @PostConstruct
    public static void initializeFirebase(){
        System.out.println("firebase config");
        try {
            if (FirebaseApp.getApps().isEmpty()) {  // Check if FirebaseApp is already initialized
                System.out.println("firebase.getApps");
                FileInputStream serviceAccount = new FileInputStream("src/main/resources/meals-app-f4782-firebase-adminsdk-cj9fe-0d85ec95d2.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setProjectId("meals-app-f4782")
                        .build();

                FirebaseApp.initializeApp(options);  // Initialize the Firebase app
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
