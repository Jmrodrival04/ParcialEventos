// DetallesTareaActivity.java
package com.example.parcialeventos;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetallesTareaActivity extends AppCompatActivity {

    private TextView textViewTaskTitle, textViewTaskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);

        textViewTaskTitle = findViewById(R.id.textViewTaskTitle);
        textViewTaskDescription = findViewById(R.id.textViewTaskDescription);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        textViewTaskTitle.setText(title);
        textViewTaskDescription.setText(description);
    }
}
