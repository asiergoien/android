package com.example.agendapersonal_asiergoienetxea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarTareaActivity extends AppCompatActivity  implements AcercaDeInterface {
    private CheckBox ch1;
    private Button btnCancelar;
    public static boolean modifiTarea = false;
    public static boolean eliTarea = false;
    public static boolean verTarea = false;

    private Button btnTodas;
    private Button btnHechas;
    private Button btnPendientes;
    private ListView LisTviewTareas;
    private ArrayList<String> tareasVista;
    private ArrayList<String> codTareas =new ArrayList<>();
    public int listviewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_tarea);
        btnTodas = findViewById(R.id.btnTodas);
        btnHechas = findViewById(R.id.btnHechas);
        btnPendientes = findViewById(R.id.btnPendientes);
        LisTviewTareas = findViewById(R.id.LisTviewTareas);
        tareasVista = ConsulTarea();
            LisTviewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listviewId = position;
                    visTarea(position, tareasVista);
                    CrearTareaPantallaVis();

                }
            });
            registerForContextMenu(LisTviewTareas);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_agenda, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("¿Qué desea hacer?");
        MenuInflater oInflater = getMenuInflater();
        oInflater.inflate(R.menu.menu_pulsacion, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicionTarea = info.position;
        int codTarea = 0;
        for (int i = 0; i < codTareas.size(); i++) {
            if (posicionTarea == i) {
                codTarea = Integer.parseInt(codTareas.get(posicionTarea));
            }
        }
            switch (item.getItemId()) {
                case R.id.Menu_modifTarea:
                    CrearTareaPantallaMod();
                    modifiTarea = true;
//                    ModTareaVis(codTarea);

                    break;
                case R.id.Menu_eliminarTarea:
                    EliminarTareaPul(codTarea);
                    eliTarea = true;
                    break;
            }
        return true;
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.CrearTarea) {
            MenuPrincipalActivity.CrearTarea=true;
            CrearTareaPantalla();
        }else if (id==R.id.CambiarContra) {
            CambiarContraPantalla();
        }
        else if (id==R.id.AcercaDe) {
            mostrarDialogo();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRespuesta(String psRespuesta) {
        Toast.makeText( this, psRespuesta, Toast.LENGTH_LONG).show();
    }


    public  ArrayList  ConsulTarea() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from TareasAgenda where usuario='" + MainActivity.usuarioGuar + "'", null);
        //Con este if lo que haremos es mirar si la select devuelve algun valor

        ArrayList<String> tareas = null;

        if (fila.moveToFirst()) {
            tareas = new ArrayList<>();
            if (fila.moveToFirst()) {
                do {
                    codTareas.add(fila.getString(0));
                    VerTareaActivity.nombreStrVer = fila.getString(2);
                    VerTareaActivity.descStrVer = fila.getString(3);
                    VerTareaActivity. FechaStrVer = fila.getString(4);
                    int  Coste = fila.getInt(5);
                    VerTareaActivity.prioStrVer = fila.getString(6);
                    VerTareaActivity.CosteStrVer= String.valueOf(Coste);
                    tareas.add(fila.getString(2));
                } while (fila.moveToNext());
            }
            bd.close();
            admin.close();
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tareas);
            LisTviewTareas.setAdapter(stringArrayAdapter);
        }
        return tareas;
    }

    public void CrearTareaPantallaMod(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,PantallaCrearActivity.class);
        startActivity(CrearPantalla);
        modifiTarea=true;

    }
    public void CrearTareaPantalla(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent CrearPantalla = new Intent(this,PantallaCrearActivity.class);
        startActivity(CrearPantalla);
        MenuPrincipalActivity.CrearTarea=true;
    }



    public void CrearTareaPantallaVis(){
        //Con esta variable intent podremos cambiar entre pantallas
        Intent verPantalla = new Intent(this,VerTareaActivity.class);
        startActivity(verPantalla);

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


    public void visTarea(int pos, ArrayList tareas){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select * from TareasAgenda where( usuario='"+MainActivity.usuarioGuar+"'and nombre='"+ tareas.get(pos) +"')",null);

        if (fila.moveToFirst()){
            do{
                PantallaCrearActivity.nombreStr = fila.getString(2);
                PantallaCrearActivity.descStr = fila.getString(3);
                PantallaCrearActivity. FechaStr = fila.getString(4);
                int  Coste = fila.getInt(5);
                PantallaCrearActivity.prioStr = fila.getString(6);
                PantallaCrearActivity.CosteStr= String.valueOf(Coste);
            }while(fila.moveToNext());
        }
        bd.close();
    }

    public void EliminarTareaPul(int codTarea){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

       int cant = bd.delete("TareasAgenda", "cod='"+codTarea+"'", null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se borró el artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        }

    }
//    public void ModTareaVis(int codTarea){
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
//        SQLiteDatabase bd = admin.getWritableDatabase();
//
//        Cursor fila = bd.rawQuery("select * from TareasAgenda where( usuario='"+MainActivity.usuarioGuar+"'and cod='"+codTarea+"')",null);
//        if (fila.moveToFirst()){
////            do{
////                PantallaCrearActivity.NombreTarea.setText(fila.getString(2));
////                PantallaCrearActivity.DescTarea.setText(fila.getString(3));
////                PantallaCrearActivity.FechaTarea.setText(fila.getString(4));
////                int  Coste = fila.getInt(5);
////                PantallaCrearActivity.prioStr = fila.getString(6);
////                PantallaCrearActivity.CosteTarea.setText(String.valueOf(Coste));
//            }while(fila.moveToNext());
//        }
//        bd.close();
    }




