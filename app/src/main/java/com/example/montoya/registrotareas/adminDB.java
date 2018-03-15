package com.example.montoya.registrotareas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Gilbe on 14/03/2018.
 */

public class adminDB extends SQLiteOpenHelper {

    private static final String NOMBREBD = "tareas.bd";
    private static final int VERSIONBD=1;

    public adminDB(Context context) {
        super(context, NOMBREBD, null, VERSIONBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LogicaBD.CREARTABLATAREAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarTarea(SQLiteDatabase db, String descripcion, String fecha, String hora, int completa){
        db.execSQL(LogicaBD.insertarTarea(descripcion, fecha, hora, completa));
        db.close();
    }

    public void editarTarea(SQLiteDatabase db, String descripcion, String fecha, String hora, int id, int completa){
        db.execSQL(LogicaBD.editarTarea(descripcion, fecha, hora, id, completa));
        db.close();
    }

    public void eliminarTarea(SQLiteDatabase db, int id){
        db.execSQL(LogicaBD.eliminarTarea(id));
        db.close();
    }

    ArrayList<tareas> listaTareas = new ArrayList<>();
    public ArrayList consultaTodo(SQLiteDatabase db){

        Cursor fila = db.rawQuery("select id, descripcion, fecha, hora, completa from tbltareas", null);
        if (fila.moveToFirst()) {
            listaTareas.clear();
            do {
                listaTareas.add(new tareas(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getInt(4)));
            }while (fila.moveToNext());
        }
        db.close();
        return listaTareas;
    }

    ArrayList<tareas> listaBuscar = new ArrayList<>();
    public ArrayList buscarID(SQLiteDatabase db, int id){

        String query = "SELECT id, descripcion, fecha, hora, completa FROM tbltareas WHERE id = " + id;

        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            listaBuscar.clear();
            do {
                listaBuscar.add(new tareas(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getInt(4)));
                System.out.println(id);
                System.out.println(fila.getString(0));
                System.out.println(fila.getString(1));
                System.out.println(fila.getString(2));
            }while (fila.moveToNext());
        }
        db.close();
        return listaBuscar;
    }
}
