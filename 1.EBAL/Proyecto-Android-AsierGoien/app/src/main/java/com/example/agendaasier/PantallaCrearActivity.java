package com.example.agendaasier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaCrearActivity extends AppCompatActivity {

    private EditText NombreTarea;
    private EditText DescTarea;
    private EditText FechaTarea;
    private EditText CosteTarea;
    private Spinner SpinnerPrioridades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_crear);
        NombreTarea=(EditText)findViewById(R.id.NomTarea);
        DescTarea=(EditText)findViewById(R.id.DescTarea);
        FechaTarea=(EditText)findViewById(R.id.FechaTarea);
        CosteTarea=(EditText)findViewById(R.id.CosteTarea);
        SpinnerPrioridades=(Spinner)findViewById(R.id.spin_Prioridades);







    }
    public void RecogerDatos(View view){
        String strNombreTarea= NombreTarea.getText().toString();
        String strDescTarea= DescTarea.getText().toString();
        String strFechaTarea= FechaTarea.getText().toString();
        String strCosteTarea= CosteTarea.getText().toString();
        String strSpinnerPrioridades= SpinnerPrioridades.getSelectedItem().toString();

    }
}