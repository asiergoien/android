package com.example.depurar_log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HolaMundo","Entramos en onCreate");
        super.onCreate(savedInstanceState);
        Object o = null;
        o.toString();
        setContentView(R.layout.activity_main);
    }
}