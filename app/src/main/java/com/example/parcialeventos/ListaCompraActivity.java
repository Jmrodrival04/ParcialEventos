package com.example.parcialeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListaCompraActivity extends AppCompatActivity {

    private EditText editTextProductName, editTextProductQuantity, editTextProductPrice;
    private Button buttonAddProduct, buttonBackToMenu;
    private TextView textViewSummary;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        // Inicializar vistas
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductQuantity = findViewById(R.id.editTextProductQuantity);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonBackToMenu = findViewById(R.id.buttonBackToMenu);
        textViewSummary = findViewById(R.id.textViewSummary);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        // Verificar que las vistas no son nulas
        if (editTextProductName == null || editTextProductQuantity == null || editTextProductPrice == null ||
                buttonAddProduct == null || buttonBackToMenu == null || textViewSummary == null || recyclerViewProducts == null) {
            Toast.makeText(this, "Error al inicializar la interfaz", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Inicializar lista de productos y adapter
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProducts.setAdapter(productAdapter);

        // Configurar botón para añadir productos
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        // Configurar botón "Volver al Menú"
        buttonBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaCompraActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addProduct() {
        String name = editTextProductName.getText().toString().trim();
        int quantity;
        double price;

        // Validación de cantidad y precio
        try {
            quantity = editTextProductQuantity.getText().toString().trim().isEmpty() ? 0 : Integer.parseInt(editTextProductQuantity.getText().toString().trim());
            price = editTextProductPrice.getText().toString().trim().isEmpty() ? 0.0 : Double.parseDouble(editTextProductPrice.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Cantidad o precio no válidos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!name.isEmpty()) {
            Product product = new Product(name, quantity, price);
            productList.add(product);
            productAdapter.notifyDataSetChanged();
            updateSummary();
            editTextProductName.setText("");
            editTextProductQuantity.setText("");
            editTextProductPrice.setText("");
        } else {
            Toast.makeText(this, "Por favor ingresa un nombre de producto", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSummary() {
        int totalProducts = productList.size();
        double totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }
        textViewSummary.setText("Total de productos: " + totalProducts + " - Precio total: " + totalPrice + "€");
    }
}
