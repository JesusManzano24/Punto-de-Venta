package com.jesusmanzano.puntodeventa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
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
        Producto producto = productos.get(position);
        holder.textViewId.setText(String.valueOf(producto.getId()));
        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewCantidad.setText(String.valueOf(producto.getCantidad()));
        holder.textViewPrecio.setText(String.valueOf(producto.getPrecio()));

        // Si producto.getImagen() devuelve un byte[], convertirlo a Bitmap y mostrarlo
        if (producto.getImagen() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(producto.getImagen(), 0, producto.getImagen().length);
            holder.imageViewImagen.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
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
