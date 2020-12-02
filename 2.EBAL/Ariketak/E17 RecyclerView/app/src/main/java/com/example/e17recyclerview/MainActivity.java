package com.example.e17recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView oRecyclerView;
    ArrayList<Contacto> contactoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Llenar el ArrayList.
        contactoArrayList = new ArrayList<Contacto>();
        for (int i = 0; i < 100; i++) {
            contactoArrayList.add(new Contacto());
        }
        oRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // inicializar el adapter con nuestros datos.
       // ContactoAdapter ca = new ContactoAdapter(contactoArrayList);
        //oRecyclerView.setAdapter(ca);
        // establecer el Layout Manager.
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        oRecyclerView.setLayoutManager(llm);
        ContactoAdapter oContactoAdapter = new ContactoAdapter(contactoArrayList, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Contacto item) {
                        Toast.makeText(MainActivity.this, "Nombre: " + item.getNombre() +"\nApellido: "+item.getApellido(), Toast.LENGTH_LONG).show();
                    }
                });
        oRecyclerView.setAdapter(oContactoAdapter);

    }
}