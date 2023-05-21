package com.example.appproductos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appproductos.ListaProductos;
import com.example.appproductos.Principal;
import com.example.appproductos.R;

public class Principal extends AppCompatActivity {
    Button Productos;
    Button Salir;
    Button LCompras;
    Button Lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Productos = findViewById(R.id.buttonListaProductos);
        Productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, ListaProductos.class);
                startActivity(i);
            }
        });
        Salir = findViewById(R.id.buttonSalir);
        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, Login.class);
                startActivity(i);
            }
        });
        LCompras = findViewById(R.id.buttonListaCompras);
        LCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, ListaCompras.class);
                startActivity(i);
            }
        });
        Lugares = findViewById(R.id.buttonLugares);
        Lugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, LugaresInteres.class);
                startActivity(i);
            }
        });
    }
}

