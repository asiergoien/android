package com.example.e60capturarclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private Button btn1;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        btn1 = (Button)findViewById(R.id.btn1);

    }
    //Este metodo se ejecutara cuando se presione el boton
    public void sumar (View view){
        String val1= et1.getText().toString();
        String val2= et2.getText().toString();
        int num1= Integer.parseInt(val1);
        int num2= Integer.parseInt(val2);
        int suma= num1+num2;

        String resu= String.valueOf(suma);
                et3.setText(resu);
    }
}