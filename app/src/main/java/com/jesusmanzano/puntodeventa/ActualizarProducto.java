package com.jesusmanzano.puntodeventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActualizarProducto extends AppCompatActivity {

    private EditText etProductoID, etPrecio, etCantidad;
    private Button btnActualizarProducto, btnRegresar;
    private OrdinarioBd dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_producto);

        etProductoID = findViewById(R.id.etProductoID);
        etPrecio = findViewById(R.id.etPrecio);
        etCantidad = findViewById(R.id.etCantidad);
        btnActualizarProducto = findViewById(R.id.btnActualizarProducto);
        btnRegresar = findViewById(R.id.Regresar);
        dbHelper = new OrdinarioBd(this);

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    int id = Integer.parseInt(etProductoID.getText().toString());
                    double precio = Double.parseDouble(etPrecio.getText().toString());
                    int cantidad = Integer.parseInt(etCantidad.getText().toString());

                    int result = dbHelper.actualizarProducto(id, precio, cantidad);
                    if (result > 0) {
                        Toast.makeText(ActualizarProducto.this, "Producto actualizado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ActualizarProducto.this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActualizarProducto.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validarCampos() {
        if (etProductoID.getText().toString().isEmpty()) {
            etProductoID.setError("Campo requerido");
            return false;
        }
        if (etPrecio.getText().toString().isEmpty()) {
            etPrecio.setError("Campo requerido");
            return false;
        }
        if (etCantidad.getText().toString().isEmpty()) {
            etCantidad.setError("Campo requerido");
            return false;
        }
        return true;
    }
}

