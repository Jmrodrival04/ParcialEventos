package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MisTareas2Activity extends AppCompatActivity {

    private Button buttonAddTask, buttonTaskList, buttonBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_tareas2);

        // Activa el botón "Volver" en el ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonTaskList = findViewById(R.id.buttonTaskList);
        buttonBackToMenu = findViewById(R.id.buttonBackToMenu);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MisTareas2Activity.this, RegistroTareaActivity.class));
            }
        });

        buttonTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MisTareas2Activity.this, ListaTareasActivity.class));
            }
        });

        // Configuración del botón "Volver al Menú"
        buttonBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MisTareas2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
