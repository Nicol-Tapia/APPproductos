package com.example.appproductos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appproductos.db.DbProductos;
import com.example.appproductos.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtCantidad, txtLugar,txtCategoria;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Productos productos;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtLugar = findViewById(R.id.txtLugar);
        txtCategoria = findViewById(R.id.txtCategoria);

        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbProductos dbProductos = new DbProductos(EditarActivity.this);
        productos = dbProductos.verProductos(id);

        if (productos != null) {
            txtNombre.setText(productos.getNombre());
            txtPrecio.setText(productos.getPrecio());
            txtCantidad.setText(productos.getCantidad());
            txtLugar.setText(productos.getLugar());
            txtCategoria.setText(productos.getCategoria());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtCantidad.getText().toString().equals("")&& !txtLugar.getText().toString().equals("")&& !txtCategoria.getText().toString().equals("")) {
                    correcto = dbProductos.editarProductos(id, txtNombre.getText().toString(), txtPrecio.getText().toString(), txtCantidad.getText().toString(), txtLugar.getText().toString(), txtCategoria.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}