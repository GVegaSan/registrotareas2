package com.example.montoya.registrotareas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
    Button confirmar;
    Button regresar;
    String[] tareasArreglo;
    ArrayList<String> tareasList;

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

    public void cargaArchivo()
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("textfile.txt")));
            for(int i = 0;i<tareasArreglo.length-1;i++)
            {
                tareasArreglo[i] = br.readLine();
            }
            br.close();
            for(int i = 0;i<tareasArreglo.length;i++)
            {
                System.out.print(tareasArreglo[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_actividad);

        descripcion = (EditText) findViewById(R.id.descripcion);
        fecha = (EditText) findViewById(R.id.elegirFecha);
        confirmar = (Button)findViewById(R.id.btnConfirmar);
        regresar = (Button)findViewById(R.id.btnRegresar);
        hora = (EditText)findViewById(R.id.elegirHora);








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
                //tareasArreglo[0] = "Hola ";
                ArrayList<String> tareasList = new ArrayList<>();
                tareasList.add(descripcion.getText() + ", " + String.valueOf(hora.getText()) + ", " + String.valueOf(fecha.getText()));

                try {
                    File myFile = new File(Environment.getExternalStorageDirectory().getPath()+"/textfile.txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    for(int i=0; tareasList.size()+1 > i; i++)
                    {
                        myOutWriter.write("hola" + "");
                    }
                    myOutWriter.close();
                    fOut.close();

                        System.out.print(tareasList);

                } catch (Exception e) {
                    e.printStackTrace();
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
}
