package com.example.montoya.registrotareas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class editar_actividad extends AppCompatActivity {

    EditText descripcionE, fechaE, horaE;
    ImageView btnConfirmarE, btnRegresarE, btnEliminarE;
    adminDB admin;
    ArrayList<tareas> listaBuscar;
    CheckBox cbCompleta;

    Calendar miCalendario = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener fechaPicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            miCalendario.set(Calendar.YEAR, year);
            miCalendario.set(Calendar.MONTH, monthOfYear);
            miCalendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarFecha();
        }
    };

    private void actualizarFecha(){
        String miFormato = "MM/dd/YYYY";
        SimpleDateFormat formatoFecha = new SimpleDateFormat(miFormato, Locale.US);
        fechaE.setText(formatoFecha.format(miCalendario.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_actividad);
        descripcionE = (EditText)findViewById(R.id.descripcionE);
        fechaE = (EditText)findViewById(R.id.elegirFechaE);
        horaE = (EditText)findViewById(R.id.elegirHoraE);
        btnConfirmarE = (ImageView)findViewById(R.id.btnConfirmarE);
        btnRegresarE = (ImageView)findViewById(R.id.btnRegresarE);
        btnEliminarE = (ImageView)findViewById(R.id.btnEliminarE);
        cbCompleta = (CheckBox)findViewById(R.id.cbCompletaE);

        fechaE.setInputType(InputType.TYPE_NULL);

        fechaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(editar_actividad.this, fechaPicker, miCalendario
                        .get(Calendar.YEAR), miCalendario.get(Calendar.MONTH),
                        miCalendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Bundle datos = getIntent().getExtras();
        int posicionTarea = datos.getInt("posicion");
        int id = datos.getInt("id");

        listaBuscar =  new ArrayList<>();

        admin = new adminDB(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(listaBuscar.isEmpty()){
            listaBuscar = admin.buscarID(db, id);
        }

        System.out.println(listaBuscar);

        descripcionE.setText(listaBuscar.get(0).getDescripcion().toString());
        fechaE.setText(listaBuscar.get(0).getFecha().toString());
        horaE.setText(listaBuscar.get(0).getHora().toString());
        int completa = listaBuscar.get(0).getCompleta();
        if(completa == 1){
            cbCompleta.setChecked(true);
        }

        btnRegresarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(editar_actividad.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnConfirmarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!descripcionE.getText().toString().equals("") && !fechaE.getText().toString().equals("") && !horaE.getText().toString().equals("")){
                    String descripcionS = descripcionE.getText().toString();
                    String fechaS = fechaE.getText().toString();
                    String horaS = horaE.getText().toString();
                    int completa = 0;
                    if(cbCompleta.isChecked()){
                        completa = 1;
                    }

                    Bundle datos = getIntent().getExtras();
                    int posicionTarea = datos.getInt("posicion");
                    int id = datos.getInt("id");

                    SQLiteDatabase db = admin.getWritableDatabase();

                    admin.editarTarea(db, descripcionS, fechaS, horaS, id, completa);

                    Toast.makeText(editar_actividad.this, "Tarea editada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(editar_actividad.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle datos = getIntent().getExtras();
                int posicionTarea = datos.getInt("posicion");
                int id = datos.getInt("id");

                SQLiteDatabase db = admin.getWritableDatabase();
                admin.eliminarTarea(db, id);
                Toast.makeText(editar_actividad.this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(editar_actividad.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
