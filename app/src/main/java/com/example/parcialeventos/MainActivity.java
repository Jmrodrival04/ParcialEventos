// MainActivity.java
package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonEjercicio1, buttonEjercicio4, buttonEjercicio6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
