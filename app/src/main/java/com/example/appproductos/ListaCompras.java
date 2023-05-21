package com.example.appproductos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appproductos.adaptadores.ListaProductosAdapter;
import com.example.appproductos.db.DbProductos;
import com.example.appproductos.entidades.Productos;

import java.util.ArrayList;

public class ListaCompras extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;
    ListaProductosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacompras);
        listaProductos = findViewById(R.id.listaContactos);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(ListaCompras.this);

        listaArrayProductos = new ArrayList<>();

        adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
