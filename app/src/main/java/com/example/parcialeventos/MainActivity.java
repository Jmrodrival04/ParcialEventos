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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button buttonEjercicio1, buttonEjercicio4, buttonEjercicio6;
    private FirebaseAnalytics firebaseAnalytics;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Inicializar la referencia a la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

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

        // Ejemplo de lectura de datos de Firebase Realtime Database
        readDataFromFirebase();
    }

    // Método para leer datos de Firebase Realtime Database
    private void readDataFromFirebase() {
        databaseReference.child("exampleNode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                Log.d("FirebaseData", "Data from Firebase: " + data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseData", "Error al leer datos de Firebase", databaseError.toException());
            }
        });
    }
}
