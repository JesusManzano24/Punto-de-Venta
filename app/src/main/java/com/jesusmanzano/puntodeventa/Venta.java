package com.jesusmanzano.puntodeventa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Venta extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        recyclerViewProductos = findViewById(R.id.recycler_view_productos);
        listaProductos = obtenerProductosDesdeBaseDeDatos();

        productoAdapter = new ProductoAdapter(listaProductos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProductos.setLayoutManager(layoutManager);
        recyclerViewProductos.setAdapter(productoAdapter);
    }

    // Método para obtener la lista de productos desde la base de datos
    private List<Producto> obtenerProductosDesdeBaseDeDatos() {
        OrdinarioBd db = new OrdinarioBd(this);
        return db.obtenerTodosLosProductos();
    }
}
