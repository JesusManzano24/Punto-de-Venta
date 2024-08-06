package com.jesusmanzano.puntodeventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inventario extends AppCompatActivity {
    private Button btnInsertarProducto, btnEliminarProducto, btnActualizarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        btnInsertarProducto = findViewById(R.id.btnInsertarProducto);
        btnEliminarProducto = findViewById(R.id.btnEliminarProducto);
        btnActualizarProducto = findViewById(R.id.btnActualizarProducto);

        btnInsertarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inventario.this, InsertarProducto.class);
                startActivity(intent);
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inventario.this, EliminarProducto.class);
                startActivity(intent);
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inventario.this, ActualizarProducto.class);
                startActivity(intent);
            }
        });
    }
}
