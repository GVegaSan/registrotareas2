package com.example.montoya.registrotareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    Button agregarTarea;

    String[] prueba;
    ListView tareas;
    int tamaño = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prueba = getResources().getStringArray(R.array.arrayPrueba);

        agregarTarea = (Button)findViewById(R.id.btnAgregar);
        tareas = (ListView)findViewById(R.id.lvTareas);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, prueba);
        tareas.setAdapter(adaptador);

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, agregar_actividad.class);
                startActivity(i);
            }
        });
    }
}
