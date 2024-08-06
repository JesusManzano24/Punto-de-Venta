package com.jesusmanzano.puntodeventa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class OrdinarioBd extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OrdinarioBD";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRODUCTOS = "productos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_CANTIDAD = "cantidad";
    private static final String COLUMN_IMAGEN = "imagen";
    private static final String COLUMN_FECHA = "fecha";

    private static final String TABLE_VENTAS = "ventas";
    private static final String COLUMN_ID_PRODUCTO = "idProducto";
    private static final String COLUMN_CANTIDAD_VENTA = "cantidad";
    private static final String COLUMN_PRECIO_VENTA = "precio";
    private static final String COLUMN_IMPORTE_VENTA = "importe";

    public OrdinarioBd(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableProductos = "CREATE TABLE " + TABLE_PRODUCTOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_PRECIO + " REAL, " +
                COLUMN_CANTIDAD + " INTEGER, " +
                COLUMN_IMAGEN + " BLOB, " +
                COLUMN_FECHA + " TEXT)";
        db.execSQL(createTableProductos);

        String createTableVentas = "CREATE TABLE " + TABLE_VENTAS + " (" +
                COLUMN_ID_PRODUCTO + " INTEGER, " +
                COLUMN_CANTIDAD_VENTA + " INTEGER, " +
                COLUMN_PRECIO_VENTA + " REAL, " +
                COLUMN_IMPORTE_VENTA + " REAL)";
        db.execSQL(createTableVentas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENTAS);
        onCreate(db);
    }

    public long insertarProducto(String nombre, double precio, int cantidad, byte[] imagen, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_PRECIO, precio);
        values.put(COLUMN_CANTIDAD, cantidad);
        values.put(COLUMN_IMAGEN, imagen);
        values.put(COLUMN_FECHA, fecha);
        return db.insert(TABLE_PRODUCTOS, null, values);
    }

    public int eliminarProducto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCTOS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int actualizarProducto(int id, double precio, int cantidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRECIO, precio);
        values.put(COLUMN_CANTIDAD, cantidad);
        return db.update(TABLE_PRODUCTOS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTOS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECIO));
                int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CANTIDAD));
                byte[] imagen = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA));

                Producto producto = new Producto(id, nombre, precio, cantidad, imagen, fecha);
                productos.add(producto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productos;
    }

    public void insertarVenta(int idProducto, int cantidad, double precio, double importe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_PRODUCTO, idProducto);
        values.put(COLUMN_CANTIDAD_VENTA, cantidad);
        values.put(COLUMN_PRECIO_VENTA, precio);
        values.put(COLUMN_IMPORTE_VENTA, importe);
        db.insert(TABLE_VENTAS, null, values);
    }

    public List<VentaItem> obtenerVentas() {
        List<VentaItem> ventas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VENTAS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idProducto = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_PRODUCTO));
                int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CANTIDAD_VENTA));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECIO_VENTA));
                double importe = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_IMPORTE_VENTA));

                VentaItem ventaItem = new VentaItem(idProducto, cantidad, precio, importe);
                ventas.add(ventaItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ventas;
    }

    public double obtenerTotalVenta() {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_IMPORTE_VENTA + ") as total FROM " + TABLE_VENTAS, null);

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("total"));
        }
        cursor.close();
        return total;
    }
}

