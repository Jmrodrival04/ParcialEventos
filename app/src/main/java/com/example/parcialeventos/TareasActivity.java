package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TareasActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAddTask, buttonPending, buttonDone, buttonBackToMenu;
    private ListView listViewTasks;
    private ArrayAdapter<String> tasksAdapter;
    private ArrayList<String> pendingTasks;
    private ArrayList<String> doneTasks;
    private boolean showingPending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonPending = findViewById(R.id.buttonPending);
        buttonDone = findViewById(R.id.buttonDone);
        buttonBackToMenu = findViewById(R.id.buttonBackToMenu);
        listViewTasks = findViewById(R.id.listViewTasks);

        pendingTasks = new ArrayList<>();
        doneTasks = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingTasks);
        listViewTasks.setAdapter(tasksAdapter);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        // Cambiar a onItemClickListener para mostrar el PopupMenu al hacer clic
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });

        buttonPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPendingTasks();
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDoneTasks();
            }
        });

        // Configuración del botón "Volver al Menú"
        buttonBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TareasActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            pendingTasks.add(task);
            editTextTask.setText("");
            tasksAdapter.notifyDataSetChanged();
        }
    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.task_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_done) {
                    markTaskAsDone(position);
                    return true;
                } else if (itemId == R.id.menu_delete) {
                    deleteTask(position);
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    private void markTaskAsDone(int position) {
        String task = tasksAdapter.getItem(position);
        if (showingPending) {
            pendingTasks.remove(task);
            doneTasks.add(task);
            Toast.makeText(this, "Tarea marcada como hecha", Toast.LENGTH_SHORT).show();
        } else {
            doneTasks.remove(task);
            pendingTasks.add(task);
            Toast.makeText(this, "Tarea devuelta a pendientes", Toast.LENGTH_SHORT).show();
        }
        tasksAdapter.notifyDataSetChanged();
    }

    private void deleteTask(int position) {
        String task = tasksAdapter.getItem(position);
        if (showingPending) {
            pendingTasks.remove(task);
        } else {
            doneTasks.remove(task);
        }
        tasksAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
    }

    private void showPendingTasks() {
        showingPending = true;
        tasksAdapter.clear();
        tasksAdapter.addAll(pendingTasks);
        tasksAdapter.notifyDataSetChanged();
    }

    private void showDoneTasks() {
        showingPending = false;
        tasksAdapter.clear();
        tasksAdapter.addAll(doneTasks);
        tasksAdapter.notifyDataSetChanged();
    }
}
