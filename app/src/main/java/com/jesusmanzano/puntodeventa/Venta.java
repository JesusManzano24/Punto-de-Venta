package com.jesusmanzano.puntodeventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Venta extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private List<OrdinarioBd.Producto> listaProductos;
    private Button botonFinalizarVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        recyclerViewProductos = findViewById(R.id.recycler_view_productos);
        botonFinalizarVenta = findViewById(R.id.boton_finalizar_venta);

        listaProductos = obtenerProductosDesdeBaseDeDatos();

        productoAdapter = new ProductoAdapter(listaProductos, this); // Pasar contexto al adaptador
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProductos.setLayoutManager(layoutManager);
        recyclerViewProductos.setAdapter(productoAdapter);

        botonFinalizarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Venta.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    private List<OrdinarioBd.Producto> obtenerProductosDesdeBaseDeDatos() {
        OrdinarioBd db = new OrdinarioBd(this);
        return db.obtenerTodosLosProductos();
    }
}


