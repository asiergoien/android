package com.elorrieta.ariketa1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView texto = new TextView(this);
        texto.setText("Hello, android");
        setContentView(texto);
    }
}

