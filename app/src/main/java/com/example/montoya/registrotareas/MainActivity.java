package com.example.montoya.registrotareas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Button agregarTarea;
    ImageView agregarTarea;

    String[] prueba;
    ListView tareas;
    int tamaño = 0;
    adminDB admin;

    ArrayList<tareas> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTareas =  new ArrayList<>();

        prueba = getResources().getStringArray(R.array.arrayPrueba);

        agregarTarea = (ImageView)findViewById(R.id.ivAgregar);
        //agregarTarea = (Button)findViewById(R.id.btnAgregar);
        tareas = (ListView)findViewById(R.id.lvTareas);

        admin = new adminDB(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(listaTareas.isEmpty()){
            listaTareas = admin.consultaTodo(db);
            adaptadorLista adaptadorBD = new adaptadorLista(this, listaTareas);
            tareas.setAdapter(adaptadorBD);
        }


        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, agregar_actividad.class);
                startActivity(i);
            }
        });

        tareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent i = new Intent(MainActivity.this, editar_actividad.class);
                    int id = listaTareas.get(position).getId();
                    System.out.println("el id en tareas es = " + id);
                    if(!listaTareas.isEmpty()) {
                        i.putExtra("posicion", position);
                        i.putExtra("id", id);
                    }
                    startActivity(i);
            }
        });
    }
}
