package com.jesusmanzano.puntodeventa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InsertarProducto extends AppCompatActivity {

    private EditText etNombre, etPrecio, etCantidad;
    private ImageView imgProducto;
    private Button btnSeleccionarImagen, btnGuardarProducto, btnRegresar;
    private OrdinarioBd dbHelper;
    private byte[] imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_producto);

        etNombre = findViewById(R.id.etNombre);
        etPrecio = findViewById(R.id.etPrecio);
        etCantidad = findViewById(R.id.etCantidad);
        imgProducto = findViewById(R.id.imgProducto);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnGuardarProducto = findViewById(R.id.btnGuardarProducto);
        btnRegresar = findViewById(R.id.Regresar);
        dbHelper = new OrdinarioBd(this);

        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para seleccionar imagen desde la galería
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    String nombre = etNombre.getText().toString();
                    double precio = Double.parseDouble(etPrecio.getText().toString());
                    int cantidad = Integer.parseInt(etCantidad.getText().toString());
                    String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                    long result = dbHelper.insertarProducto(nombre, precio, cantidad, imagen, fecha);
                    if (result != -1) {
                        Toast.makeText(InsertarProducto.this, "Producto guardado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(InsertarProducto.this, "Error al guardar el producto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertarProducto.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imgProducto.setImageBitmap(bitmap);
                imagen = bitmapToByteArray(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private boolean validarCampos() {
        if (etNombre.getText().toString().isEmpty()) {
            etNombre.setError("Campo requerido");
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
        if (imagen == null) {
            Toast.makeText(this, "Seleccione una imagen", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
