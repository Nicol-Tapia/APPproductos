package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appproductos.db.DbProductos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtCantidad, txtLugar, txtCategoria;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtLugar = findViewById(R.id.txtLugar);
        txtCategoria = findViewById(R.id.txtCategoria);

        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtCantidad.getText().toString().equals("")&& !txtLugar.getText().toString().equals("")&& !txtCategoria.getText().toString().equals("")) {

                    DbProductos dbProductos = new DbProductos(NuevoActivity.this);
                    long id = dbProductos.insertarProductos(txtNombre.getText().toString(), txtPrecio.getText().toString(), txtCantidad.getText().toString(), txtLugar.getText().toString(), txtCategoria.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtLugar.setText("");
        txtCategoria.setText("");

    }
}