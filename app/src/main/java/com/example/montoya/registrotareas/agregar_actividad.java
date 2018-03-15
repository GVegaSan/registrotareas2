package com.example.montoya.registrotareas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class agregar_actividad extends AppCompatActivity {



    EditText descripcion;
    EditText fecha;
    Calendar miCalendario = Calendar.getInstance();
    EditText hora;
    ImageView confirmar;
    ImageView regresar;
    String[] tareasArreglo;
    ArrayList<String> tareasList;
    adminDB admin;

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
        fecha.setText(formatoFecha.format(miCalendario.getTime()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_actividad);

        descripcion = (EditText) findViewById(R.id.descripcion);
        fecha = (EditText) findViewById(R.id.elegirFecha);
        confirmar = (ImageView) findViewById(R.id.btnConfirmar);
        regresar = (ImageView) findViewById(R.id.btnRegresar);
        hora = (EditText)findViewById(R.id.elegirHora);

        fecha.setInputType(InputType.TYPE_NULL);




        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(agregar_actividad.this, fechaPicker, miCalendario
                        .get(Calendar.YEAR), miCalendario.get(Calendar.MONTH),
                        miCalendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!descripcion.getText().toString().equals("") && !fecha.getText().toString().equals("") && !hora.getText().toString().equals("")){
                    String descripcionS = descripcion.getText().toString();
                    String fechaS = fecha.getText().toString();
                    String horaS = hora.getText().toString();

                    insertarTarea(descripcionS, fechaS, horaS, 0);
                }else{
                    Toast.makeText(agregar_actividad.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(agregar_actividad.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void insertarTarea(String descripcion,String fecha,String hora, int completa){
        admin = new adminDB(this);
        SQLiteDatabase db = admin.getWritableDatabase();
        admin.insertarTarea(db,descripcion, fecha, hora, completa);
        Toast.makeText(this, "Tarea agregada", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(agregar_actividad.this, MainActivity.class);
        startActivity(i);
    }
}
