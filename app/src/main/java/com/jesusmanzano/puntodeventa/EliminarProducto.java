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

public class EliminarProducto extends AppCompatActivity {

    private EditText etProductoID;
    private Button btnEliminarProducto, btnRegresar;
    private OrdinarioBd dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);

        etProductoID = findViewById(R.id.etProductoID);
        btnEliminarProducto = findViewById(R.id.btnEliminarProducto);
        btnRegresar = findViewById(R.id.Regresar);
        dbHelper = new OrdinarioBd(this);

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    int id = Integer.parseInt(etProductoID.getText().toString());

                    int result = dbHelper.eliminarProducto(id);
                    if (result > 0) {
                        Toast.makeText(EliminarProducto.this, "Producto eliminado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EliminarProducto.this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EliminarProducto.this, MainActivity3.class);
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
        return true;
    }
}
