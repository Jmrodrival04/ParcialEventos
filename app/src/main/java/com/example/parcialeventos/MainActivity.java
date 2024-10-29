// MainActivity.java
package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button buttonEjercicio1, buttonEjercicio4, buttonEjercicio6;
    private FirebaseAnalytics firebaseAnalytics;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Inicializar Firestore
        firestore = FirebaseFirestore.getInstance();

        // Registrar un evento en Firebase Analytics
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, "MainActivity Opened");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        // Configurar botones para navegar a diferentes ejercicios
        buttonEjercicio1 = findViewById(R.id.buttonEjercicio1);
        buttonEjercicio4 = findViewById(R.id.buttonEjercicio4);
        buttonEjercicio6 = findViewById(R.id.buttonEjercicio6);

        buttonEjercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TareasActivity.class));
            }
        });

        buttonEjercicio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListaCompraActivity.class));
            }
        });

        buttonEjercicio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MisTareas2Activity.class));
            }
        });

        // Ejemplo de escritura en Firebase Firestore
        writeDataToFirestore();

        // Ejemplo de lectura de datos desde Firebase Firestore
        readDataFromFirestore();
    }

    // Método para escribir datos en Firestore
    private void writeDataToFirestore() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Sample Item");
        data.put("description", "This is a sample description");

        // Agregar datos a una colección llamada "exampleCollection"
        firestore.collection("exampleCollection").add(data)
                .addOnSuccessListener(documentReference -> Log.d("FirestoreData", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.e("FirestoreData", "Error adding document", e));
    }

    // Método para leer datos desde Firestore
    private void readDataFromFirestore() {
        firestore.collection("exampleCollection")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            Log.d("FirestoreData", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.e("FirestoreData", "Error getting documents", task.getException());
                    }
                });
    }
}
