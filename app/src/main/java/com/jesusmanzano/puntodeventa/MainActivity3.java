package com.jesusmanzano.puntodeventa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
    private Button btnVenta, btnInventario, btnSalir;
    private TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnVenta = findViewById(R.id.btnVenta);
        btnInventario = findViewById(R.id.btnInventario);
        btnSalir = findViewById(R.id.btnSalir);
        nombre = findViewById(R.id.nombre);

        // Recuperar el nombre del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String usuario = prefs.getString("usuario", "Usuario");

        // Obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        String greeting;

        // Determinar el saludo según la hora del día
        if (hourOfDay >= 6 && hourOfDay < 12) {
            greeting = "Buenos días";
        } else if (hourOfDay >= 12 && hourOfDay < 18) {
            greeting = "Buenas tardes";
        } else {
            greeting = "Buenas noches";
        }

        // Establecer el saludo en el TextView
        nombre.setText(greeting + ", " + usuario);

        btnVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, Venta.class);
                startActivity(intent);
            }
        });

        btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, Inventario.class);
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Cierra la aplicación
            }
        });
    }
}
