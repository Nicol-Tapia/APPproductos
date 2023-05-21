package com.example.appproductos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appproductos.R;
import com.example.appproductos.VerActivity;
import com.example.appproductos.entidades.Productos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductosViewHolder> {

    ArrayList<Productos> listaProductos;
    ArrayList<Productos> listaOriginal;

    public ListaProductosAdapter(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaProductos);
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_productos, null, false);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        holder.viewNombre.setText(listaProductos.get(position).getNombre());
        holder.viewPrecio.setText(listaProductos.get(position).getPrecio());
        holder.viewCantidad.setText(listaProductos.get(position).getCantidad());
        holder.viewLugar.setText(listaProductos.get(position).getLugar());
        holder.viewCategoria.setText(listaProductos.get(position).getCategoria());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaProductos.clear();
            listaProductos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Productos> collecion = listaProductos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collecion);
            } else {
                for (Productos c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaProductos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewPrecio, viewCantidad, viewLugar, viewCategoria;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);
            viewLugar = itemView.findViewById(R.id.viewLugar);
            viewCategoria = itemView.findViewById(R.id.viewCategoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
