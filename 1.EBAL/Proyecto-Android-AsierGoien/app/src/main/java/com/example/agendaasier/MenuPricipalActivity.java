package com.example.agendaasier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPricipalActivity extends AppCompatActivity {
    public static boolean mostrarModiTarea=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_pricipal);
    }

    public void  Crear_Tarea_Pantalla(View v){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,PantallaCrearActivity.class);
        startActivity(CrearPantalla);

    }

    public void  Consultar_tarea_Pantalla(View v){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,ConsultarTareaActivity.class);
        startActivity(CrearPantalla);

    }
}