package com.meals.meals_app;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class FirebaseTokenGenerator {
    public static String createCustomToken(String uid){
        try{
            return FirebaseAuth.getInstance().createCustomToken(uid);
        }catch (FirebaseAuthException e){
            e.printStackTrace();
            return null;
        }
    }
}
