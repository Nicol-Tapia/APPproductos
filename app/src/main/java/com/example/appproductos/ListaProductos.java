package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.appproductos.adaptadores.ListaProductosAdapter;
import com.example.appproductos.db.DbProductos;
import com.example.appproductos.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;
    FloatingActionButton fabNuevo;
    ListaProductosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaproductos);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaProductos = findViewById(R.id.listaContactos);
        fabNuevo = findViewById(R.id.favNuevo);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(ListaProductos.this);

        listaArrayProductos = new ArrayList<>();

        adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}