package com.example.agendapersonal_asiergoienetxea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaCrearActivity extends AppCompatActivity {
    public ObjetoTarea ob1= null;

    public EditText NombreTarea;
    public EditText DescTarea;
    public EditText FechaTarea;
    public EditText CosteTarea;
    public Spinner SpinnerPrioridades;
    private CheckBox chTarea_realizada;
    private Button btnEliminarTarea;
    public static TextView TextViewPrioridades;
    public static  String nombreStr;
    public static String descStr;
    public static String FechaStr;
    public static String CosteStr;
    public static String prioStr;
    public static String realizadaStr="false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_crear);
        NombreTarea=(EditText)findViewById(R.id.NomTarea);
        DescTarea=(EditText)findViewById(R.id.DescTarea);
        FechaTarea=(EditText)findViewById(R.id.FechaTarea);
        CosteTarea=(EditText)findViewById(R.id.CosteTarea);
        TextViewPrioridades=(TextView)findViewById(R.id.TextViewPrioridades);
        SpinnerPrioridades=(Spinner)findViewById(R.id.spin_Prioridades);
        chTarea_realizada = (CheckBox)findViewById(R.id.chTarea_realizada);
        btnEliminarTarea = (Button)findViewById(R.id.btnEliminarTarea);
        SpinnerPrioridades.setVisibility(View.INVISIBLE);
        chTarea_realizada.setVisibility(View.INVISIBLE);
        TextViewPrioridades.setVisibility(View.INVISIBLE);
        btnEliminarTarea.setVisibility(View.INVISIBLE);
        NombreTarea.setEnabled(false);
        NombreTarea.setText(nombreStr);
        DescTarea.setEnabled(false);
        DescTarea.setText(descStr);
        FechaTarea.setEnabled(false);
        FechaTarea.setText(FechaStr);
        CosteTarea.setEnabled(false);
        CosteTarea.setText(CosteStr);
        ModifiTarea(consultarTareasAgenda.ModifiTarea);
        CrearTarea(MenuPrincipalActivity.CrearTarea);
    }

    public void RecogerDatos(View view){
        String strNombreTarea= NombreTarea.getText().toString();
        String strDescTarea= DescTarea.getText().toString();
        String strFechaTarea= FechaTarea.getText().toString();
        String strCosteTarea= CosteTarea.getText().toString();
        String strSpinnerPrioridades= SpinnerPrioridades.getSelectedItem().toString();
        int costeTarea;

        if (strCosteTarea.isEmpty()){
            strCosteTarea= "0";
        }
        if (consultarTareasAgenda.ModifiTarea==true){
            if (!(strNombreTarea.isEmpty() || strDescTarea.isEmpty() || strFechaTarea.isEmpty() || strCosteTarea.isEmpty() || strSpinnerPrioridades.isEmpty())){
                if (chTarea_realizada.isChecked()==true){
                    realizadaStr="true";
                }else{
                    realizadaStr="false";
                }
                costeTarea = Integer.parseInt(strCosteTarea);
                ob1= new ObjetoTarea (MainActivity.usuarioGuar,strNombreTarea,strDescTarea,strFechaTarea,costeTarea,strSpinnerPrioridades,realizadaStr);
                if (ModificarTareasDB(ob1)==true){
                    Toast.makeText(this,"Exito al modificar la tarea",Toast.LENGTH_LONG).show();
                    NombreTarea.setText("");
                    DescTarea.setText("");
                    FechaTarea.setText("");
                    CosteTarea.setText("");
                    finish();
                }else{
                    Toast.makeText(this,"Error de conecxion con la base datos",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this,"No se ha podido modificar la tarea.\nCompruebe todos los campos",Toast.LENGTH_LONG).show();
            }
        }else if (!(strNombreTarea.isEmpty() || strDescTarea.isEmpty() || strFechaTarea.isEmpty() || strCosteTarea.isEmpty() || strSpinnerPrioridades.isEmpty())){
             costeTarea = Integer.parseInt(strCosteTarea);
            ob1= new ObjetoTarea (MainActivity.usuarioGuar,strNombreTarea,strDescTarea,strFechaTarea,costeTarea,strSpinnerPrioridades,realizadaStr);
             if (InsertTareasDB(ob1)==true){
                Toast.makeText(this,"Exito al registrar la tarea",Toast.LENGTH_LONG).show();
                NombreTarea.setText("");
                DescTarea.setText("");
                FechaTarea.setText("");
                CosteTarea.setText("");
                finish();
            }else {
               Toast.makeText(this,"Error de conecxion con la base datos",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"No se ha podido crear la tarea.\n Compruebe todos los campos",Toast.LENGTH_LONG).show();
        }

        MenuPrincipalActivity.CrearTarea=false;

    }
    public boolean InsertTareasDB(ObjetoTarea ob1){
        boolean bol = false;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("usuario",ob1.getUsuario());
        registro.put("nombre", ob1.getNomTarea());
        registro.put("descripcion", ob1.getDescTarea());
        registro.put("fecha", ob1.getFechaTarea());
        registro.put("coste", ob1.getCosteTarea());
        registro.put("prioridad", ob1.getSpinnerPrio());
        registro.put("realizada", ob1.getRealizada());


        if ( bd.insert("TareasAgenda", null, registro) ==-1){
        bol=false;
        }else{
            bol= true;

            bd.close();
            admin.close();
        }
        return bol;
    }
    public boolean ModificarTareasDB(ObjetoTarea ob1){
        boolean bol = false;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("usuario",ob1.getUsuario());
        registro.put("nombre", ob1.getNomTarea());
        registro.put("descripcion", ob1.getDescTarea());
        registro.put("fecha", ob1.getFechaTarea());
        registro.put("coste", ob1.getCosteTarea());
        registro.put("prioridad", ob1.getSpinnerPrio());
        registro.put("realizada", ob1.getRealizada());

        if (bd.update("TareasAgenda",registro,"nombre='"+nombreStr+"' and usuario ='"+MainActivity.usuarioGuar+"'",null) ==-1){
        //if (bd.update("TareasAgenda",registro,"usuario= ?"+"AND"+" nombre=?",new String[]{MainActivity.usuarioGuar,ob1.getNomTarea()}) ==-1){
            //db.update(TABLE_NAME, contentValues, NAME + " = ? AND " + LASTNAME + " = ?", new String[]{"Manas", "Bajaj"});
            bol=false;
        }else{
            bol= true;
            bd.close();
            admin.close();
        }
        return bol;
    }
    public void Salir(View view){

        NombreTarea.setText("");
        DescTarea.setText("");
        FechaTarea.setText("");
        CosteTarea.setText("");
        MenuPrincipalActivity.CrearTarea=false;
        finish();
    }
    public void ModifiTarea(Boolean modTarea){
        if (modTarea==true) {
            SpinnerPrioridades.setVisibility(View.VISIBLE);
            chTarea_realizada.setVisibility(View.VISIBLE);
            TextViewPrioridades.setVisibility(View.INVISIBLE);
            btnEliminarTarea.setVisibility(View.VISIBLE);
            NombreTarea.setEnabled(true);
            DescTarea.setEnabled(true);
            FechaTarea.setEnabled(true);
            CosteTarea.setEnabled(true);

            if (realizadaStr.equalsIgnoreCase("true")){

                chTarea_realizada.setChecked(true);
            }
            if (prioStr.equalsIgnoreCase("urgente")){
                Log.i("", "ModifiTarea: urgente");
                SpinnerPrioridades.setSelection(0);
            }else if(prioStr.equalsIgnoreCase("alta")) {
                Log.i("", "ModifiTarea: alta");
                SpinnerPrioridades.setSelection(1);
            }else if (prioStr.equalsIgnoreCase("media")){
                Log.i("", "ModifiTarea: media");
                SpinnerPrioridades.setSelection(2);
            }else{
                Log.i("", "ModifiTarea: baja");
                SpinnerPrioridades.setSelection(3);
            }

        }
    }
    public void elimiarTarea(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        int cant = bd.delete("TareasAgenda", "nombre='"+nombreStr+"' and usuario ='"+MainActivity.usuarioGuar+"'", null);
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
    public void CrearTarea(Boolean creartarea) {
        if(creartarea==true){
            NombreTarea.setText("");
            CosteTarea.setText("");
            DescTarea.setText("");
            FechaTarea.setText("");
            NombreTarea.setEnabled(true);
            DescTarea.setEnabled(true);
            FechaTarea.setEnabled(true);
            CosteTarea.setEnabled(true);
            SpinnerPrioridades.setVisibility(View.VISIBLE);
            chTarea_realizada.setVisibility(View.INVISIBLE);
            TextViewPrioridades.setVisibility(View.INVISIBLE);
            btnEliminarTarea.setVisibility(View.INVISIBLE);

        }
    }


}