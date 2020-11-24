package com.example.e7_0_listviewagregaryquitarelementos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> o_ArrayList;
    private ArrayAdapter<String> o_ArrayAdapter;
    private ListView o_ListView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--
        o_ArrayList = new ArrayList<String>();
        o_ArrayList.add("marcos : 43734843");
        o_ArrayList.add("luis : 6554343");
        o_ArrayList.add("ana : 7445434");
        o_ArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, o_ArrayList);
        o_ListView = (ListView)findViewById(R.id.listView);
        o_ListView.setAdapter(o_ArrayAdapter);
        //--
        editText = (EditText)findViewById(R.id.editText);

        //--
        o_ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View
                    view, final int piPosicion, long l) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(MainActivity.this);
                oDialog.setTitle("Importante");
                oDialog.setMessage("¿Desea eliminar este teléfono?");
                oDialog.setCancelable(false);
                oDialog.setPositiveButton("Confirmar", new
                        DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        o_ArrayList.remove(piPosicion);
                        o_ArrayAdapter.notifyDataSetChanged();
                    }
                });
                oDialog.setNegativeButton("Cancelar", new
                        DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                oDialog.show();
                return false;
            }
        });
    }
    public void agregar(View poView) {
        o_ArrayList.add(editText.getText().toString());
        o_ArrayAdapter.notifyDataSetChanged();
        editText.setText("");

    }


}