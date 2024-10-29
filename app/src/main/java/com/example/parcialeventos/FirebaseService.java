package com.example.parcialeventos;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseService extends Application {

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);

        // Inicializar Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public FirebaseAnalytics getFirebaseAnalytics() {
        return firebaseAnalytics;
    }
}
