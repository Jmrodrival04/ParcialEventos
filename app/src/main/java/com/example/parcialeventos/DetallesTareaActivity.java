package com.example.parcialeventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetallesTareaActivity extends AppCompatActivity {

    private TextView textViewTaskDetails;
    private Button buttonBackToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);

        textViewTaskDetails = findViewById(R.id.textViewTaskDetails);
        buttonBackToList = findViewById(R.id.buttonBackToList);

        // Obtener los detalles de la tarea desde el Intent
        String taskDetails = getIntent().getStringExtra("taskDetails");
        textViewTaskDetails.setText(taskDetails);

        // Configurar el botón de volver a ListaTareasActivity
        buttonBackToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a ListaTareasActivity
            }
        });
    }
}
