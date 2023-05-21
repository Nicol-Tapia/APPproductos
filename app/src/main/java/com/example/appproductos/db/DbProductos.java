package com.example.appproductos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.appproductos.entidades.Productos;

import java.util.ArrayList;

public class DbProductos extends DbHelper {

    Context context;

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProductos(String nombre, String precio, String cantidad, String lugar, String categoria ) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("precio", precio);
            values.put("cantidad", cantidad);
            values.put("lugar", lugar);
            values.put("categoria", categoria);

            id = db.insert(TABLE_PRODUCTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Productos> mostrarProductos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos productos;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " ORDER BY nombre ASC", null);

        if (cursorProductos.moveToFirst()) {
            do {
                productos = new Productos();
                productos.setId(cursorProductos.getInt(0));
                productos.setNombre(cursorProductos.getString(1));
                productos.setPrecio(cursorProductos.getString(2));
                productos.setCantidad(cursorProductos.getString(3));
                productos.setLugar(cursorProductos.getString(4));
                productos.setCategoria(cursorProductos.getString(5));
                listaProductos.add(productos);
            } while (cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaProductos;
    }

    public Productos verProductos(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Productos productos = null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            productos = new Productos();
            productos.setId(cursorProductos.getInt(0));
            productos.setNombre(cursorProductos.getString(1));
            productos.setPrecio(cursorProductos.getString(2));
            productos.setCantidad(cursorProductos.getString(3));
            productos.setLugar(cursorProductos.getString(4));
            productos.setCategoria(cursorProductos.getString(5));
        }

        cursorProductos.close();

        return productos;
    }

    public boolean editarProductos(int id, String nombre, String precio, String cantidad, String lugar, String categoria) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET nombre = '" + nombre + "', precio = '" + precio + "', cantidad = '" + cantidad + "', lugar = '" + lugar + "', categoria = '" + categoria + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarProductos(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
