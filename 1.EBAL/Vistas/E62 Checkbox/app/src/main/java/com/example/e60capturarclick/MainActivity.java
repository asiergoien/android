package com.example.e60capturarclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;

    private Button bt1;

    private CheckBox ch1;
    private CheckBox ch2;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        bt1=(Button) findViewById(R.id.bt1);
        ch1= (CheckBox) findViewById(R.id.ch1);
        ch2= (CheckBox)findViewById(R.id.ch2);

    }
    //Este metodo se ejecutara cuando se presione el boton
    public void operar (View view){
        String val1= et1.getText().toString();
        String val2= et2.getText().toString();
        int num1= Integer.parseInt(val1);
        int num2= Integer.parseInt(val2);

        if (ch1.isChecked()==true){
            int suma= num1+num2;
            String resu= String.valueOf(suma);
            et3.setText(resu);
        }
        if(ch2.isChecked() == true){
            int suma= num1-num2;
            String resu= String.valueOf(suma);
            et3.setText(resu);
        }if (ch2.isChecked() == true && ch1.isChecked() == true){
            int suma= num1+num2;
            String resu= String.valueOf(suma);
            et3.setText(resu);

            int suma2= num1-num2;
            String resu2= String.valueOf(suma2);
            et3.setText("La suma es de -->"+resu+"La resta de -->"+resu2);

        }


    }
}