package com.jesusmanzano.puntodeventa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<OrdinarioBd.Producto> productos;
    private Map<Integer, Integer> cantidadesSeleccionadas = new HashMap<>();

    public ProductoAdapter(List<OrdinarioBd.Producto> productos, Venta venta) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_itemproducto, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        OrdinarioBd.Producto producto = productos.get(position);
        holder.textViewId.setText(String.valueOf(producto.getId()));
        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewCantidad.setText(String.valueOf(producto.getCantidad()));
        holder.textViewPrecio.setText(String.valueOf(producto.getPrecio()));
        holder.imageViewImagen.setImageBitmap(BitmapFactory.decodeByteArray(producto.getImagen(), 0, producto.getImagen().length));

        holder.itemView.setOnClickListener(v -> mostrarDialogoSeleccionarCantidad(holder.itemView.getContext(), producto));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    private void mostrarDialogoSeleccionarCantidad(Context context, OrdinarioBd.Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_seleccionar_cantidad, null);
        builder.setView(view);

        TextView textViewNombre = view.findViewById(R.id.text_view_dialog_producto_nombre);
        EditText editTextCantidad = view.findViewById(R.id.edit_text_dialog_cantidad);

        textViewNombre.setText(producto.getNombre());

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            int cantidadSeleccionada = Integer.parseInt(editTextCantidad.getText().toString());
            if (cantidadSeleccionada > producto.getCantidad()) {
                Toast.makeText(context, "No se puede completar la venta por falta de " + producto.getNombre(), Toast.LENGTH_SHORT).show();
            } else {
                cantidadesSeleccionadas.put(producto.getId(), cantidadSeleccionada);
                actualizarTotal(context);
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void actualizarTotal(Context context) {
        double total = 0;
        for (Map.Entry<Integer, Integer> entry : cantidadesSeleccionadas.entrySet()) {
            int productoId = entry.getKey();
            int cantidadSeleccionada = entry.getValue();
            for (OrdinarioBd.Producto producto : productos) {
                if (producto.getId() == productoId) {
                    total += producto.getPrecio() * cantidadSeleccionada;
                    break;
                }
            }
        }
        ((TextView) ((Activity) context).findViewById(R.id.total_value)).setText("Total: $" + total);
    }


    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewNombre, textViewCantidad, textViewPrecio;
        ImageView imageViewImagen;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_view_producto_id);
            textViewNombre = itemView.findViewById(R.id.text_view_producto_nombre);
            textViewCantidad = itemView.findViewById(R.id.text_view_producto_cantidad);
            textViewPrecio = itemView.findViewById(R.id.text_view_producto_precio);
            imageViewImagen = itemView.findViewById(R.id.image_view_producto_imagen);
        }
    }
}

