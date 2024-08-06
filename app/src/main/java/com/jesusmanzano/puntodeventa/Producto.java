package com.jesusmanzano.puntodeventa;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private byte[] imagen;
    private String fecha;

    public Producto(int id, String nombre, double precio, int cantidad, byte[] imagen, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public String getFecha() {
        return fecha;
    }
}

