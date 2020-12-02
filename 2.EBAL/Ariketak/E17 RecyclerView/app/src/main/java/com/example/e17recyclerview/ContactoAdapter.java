package com.example.e17recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.MyViewHolder> {
    private List<Contacto> contactsList;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public final OnItemClickListener listener;

    /**
     * Clase ViewHolder *
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTV;
        public TextView apellidoTV;
        public TextView fechaNacimientoTV;

        public MyViewHolder(View view) {
            super(view);
            nombreTV = (TextView) view.findViewById(R.id.contactName);
            apellidoTV = (TextView) view.findViewById(R.id.apellido);
            fechaNacimientoTV = (TextView) view.findViewById(R.id.birthday);
        }
    }

    // Constructor del Adaptador.
    public ContactoAdapter(List<Contacto> contactLists, OnItemClickListener listener) {
        this.contactsList = contactLists;
        this.listener = listener;
    }

    // Este metodo es llamado por el RecyclerView para mostrar los datos del elemento de esa posición.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contacto c = contactsList.get(position);
        holder.nombreTV.setText(c.getNombre());
        holder.apellidoTV.setText(c.getApellido());
        holder.fechaNacimientoTV.setText(df.format(c.getFechaNacimiento()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(c);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    // A este metodo se le llama cuando necesitamos crear una nueva linea para elRecyclerView.
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.linea, parent,
                        false);
        return new MyViewHolder(v);
    }

}
