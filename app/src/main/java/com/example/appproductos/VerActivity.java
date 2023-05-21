package com.example.appproductos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appproductos.db.DbProductos;
import com.example.appproductos.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtCantidad, txtLugar,txtCategoria;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar, fabListacompras;

    Productos productos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtLugar = findViewById(R.id.txtLugar);
        txtCategoria = findViewById(R.id.txtCategoria);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbProductos dbProductos = new DbProductos(VerActivity.this);
        productos = dbProductos.verProductos(id);

        if(productos != null){
            txtNombre.setText(productos.getNombre());
            txtPrecio.setText(productos.getPrecio());
            txtCantidad.setText(productos.getCantidad());
            txtLugar.setText(productos.getLugar());
            txtCategoria.setText(productos.getCategoria());


            txtNombre.setInputType(InputType.TYPE_NULL);
            txtPrecio.setInputType(InputType.TYPE_NULL);
            txtCantidad.setInputType(InputType.TYPE_NULL);
            txtLugar.setInputType(InputType.TYPE_NULL);
            txtCategoria.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este producto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbProductos.eliminarProductos(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, ListaProductos.class);
        startActivity(intent);
    }
}