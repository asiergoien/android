package com.example.agendaasier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarTareaActivity extends AppCompatActivity  implements AcercaDeInterface{
    private CheckBox ch1;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_tarea);


    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_agenda, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.CrearTarea) {
            CrearTareaPantalla();
        }else if (id==R.id.CambiarContra) {
            CambiarContraPantalla();
        }
        else if (id==R.id.AcercaDe) {
            mostrarDialogo();
        }

        return super.onOptionsItemSelected(item);
    }
    public void CrearTareaPantalla(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,PantallaCrearActivity.class);
        startActivity(CrearPantalla);

    }
    public void CambiarContraPantalla(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CambiarContra = new Intent(this,Pantalla_registrar.class);
        startActivity(CambiarContra);
        MainActivity.mostrarContraAnti=true;
    }

    public void mostrarDialogo() {
        DialagoAcercaDe oDialagoAcercaDe;
        oDialagoAcercaDe = new DialagoAcercaDe();
        oDialagoAcercaDe.show(getSupportFragmentManager(), "Mi Diálogo");
    }


    @Override
    public void onRespuesta(String psRespuesta) {
        Toast.makeText( this, psRespuesta, Toast.LENGTH_LONG).show();
    }
}