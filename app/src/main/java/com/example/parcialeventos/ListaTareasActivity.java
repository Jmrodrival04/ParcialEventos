package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListaTareasActivity extends AppCompatActivity {

    private ListView listViewTasks;
    private Button buttonBackToMisTareas;
    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> taskTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        // Activa el botón "Volver" en el ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listViewTasks = findViewById(R.id.listViewTasks);
        buttonBackToMisTareas = findViewById(R.id.buttonBackToMisTareas);

        // Configurar el adaptador y la lista
        taskTitles = new ArrayList<>();
        for (Task task : RegistroTareaActivity.taskList) {
            taskTitles.add(task.getTitle()); // Suponiendo que Task tiene un método getTitle()
        }
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskTitles);
        listViewTasks.setAdapter(taskAdapter);

        // Configurar el listener para el botón de volver
        buttonBackToMisTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y vuelve a MisTareas2Activity
            }
        });

        // Configurar el click en los elementos de la lista para abrir DetallesTareaActivity
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaTareasActivity.this, DetallesTareaActivity.class);
                intent.putExtra("taskDetails", RegistroTareaActivity.taskList.get(position).getDescription()); // Enviar descripción de la tarea
                startActivity(intent);
            }
        });
    }

    // Manejar el botón de volver en el ActionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Volver a MisTareas2Activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
