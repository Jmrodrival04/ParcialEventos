package com.example.parcialeventos;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaTareasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        // Activa el botón "Volver" en el ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa el adaptador con una lista de tareas
        taskAdapter = new TaskAdapter(RegistroTareaActivity.taskList, this);
        recyclerViewTasks.setAdapter(taskAdapter);
    }

    // Anulación del método para manejar el botón de retroceso en el ActionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Cierra la actividad y vuelve a la anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
