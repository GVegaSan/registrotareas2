package com.example.montoya.registrotareas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class adaptadorLista extends BaseAdapter {


    Context contexto;
    ArrayList<tareas> listaTareas;

    public adaptadorLista(Context contexto, ArrayList<tareas> listaTareas) {
        this.contexto = contexto;
        this.listaTareas = listaTareas;
    }

    @Override
    public int getCount() {
        return listaTareas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View renglon=convertView;
        if(convertView==null){
            // se infla
            LayoutInflater inflador= (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            renglon=inflador.inflate(R.layout.renglon,parent,false);
        }
        TextView descripcion=(TextView) renglon.findViewById(R.id.tvDescripcion);
        TextView fecha=(TextView) renglon.findViewById(R.id.tvFecha);
        TextView hora=(TextView) renglon.findViewById(R.id.tvHora);

        descripcion.setText(listaTareas.get(position).getDescripcion());
        fecha.setText(listaTareas.get(position).getFecha());
        hora.setText(listaTareas.get(position).getHora());

        int completa = listaTareas.get(position).getCompleta();
        if(completa == 1){
            renglon.setBackgroundColor(Color.GREEN);
        }

        return renglon;
    }
}
