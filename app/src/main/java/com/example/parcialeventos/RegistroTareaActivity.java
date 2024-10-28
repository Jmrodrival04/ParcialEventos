package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RegistroTareaActivity extends AppCompatActivity {

    private EditText editTextTaskTitle, editTextTaskDescription;
    private Button buttonSaveTask, buttonBackToMisTareas;

    // Lista estática para almacenar tareas y permitir su acceso desde otras actividades
    public static ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tarea);

        // Activa el botón "Volver" en el ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        buttonSaveTask = findViewById(R.id.buttonSaveTask);
        buttonBackToMisTareas = findViewById(R.id.buttonBackToMisTareas);

        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        // Configuración del botón "Volver a Mis Tareas"
        buttonBackToMisTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroTareaActivity.this, MisTareas2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveTask() {
        String title = editTextTaskTitle.getText().toString().trim();
        String description = editTextTaskDescription.getText().toString().trim();

        if (!title.isEmpty()) {
            Task newTask = new Task(title, description);
            taskList.add(newTask);
            Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show();
            finish();  // Regresar a la actividad anterior
        } else {
            Toast.makeText(this, "El título de la tarea es obligatorio", Toast.LENGTH_SHORT).show();
        }
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
