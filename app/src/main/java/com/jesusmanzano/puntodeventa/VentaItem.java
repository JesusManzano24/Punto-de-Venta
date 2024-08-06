package com.jesusmanzano.puntodeventa;

public class VentaItem {
    private int idProducto;
    private int cantidad;
    private double precio;
    private double importe;

    public VentaItem(int idProducto, int cantidad, double precio, double importe) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getImporte() {
        return importe;
    }
}
