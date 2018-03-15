package com.example.montoya.registrotareas;


import android.os.Parcel;
import android.os.Parcelable;

public class tareas implements Parcelable {

    private String descripcion, fecha, hora;
    private int id, completa;

    public tareas(){
        this.id = 0;
        this.descripcion = "";
        this.hora = "";
        this.fecha = "";
        this.completa = 0;
    }



    protected tareas(Parcel in) {
        id = in.readInt();
        descripcion = in.readString();
        fecha = in.readString();
        hora = in.readString();
        completa = in.readInt();
    }

    public static final Creator<tareas> CREATOR = new Creator<tareas>() {
        @Override
        public tareas createFromParcel(Parcel in) {
            return new tareas(in);
        }

        @Override
        public tareas[] newArray(int size) {
            return new tareas[size];
        }
    };

    public tareas(int id, String descripcion, String fecha, String hora, int completa){
        this.id = id;
        this.descripcion = descripcion;
        this.hora = hora;
        this.fecha = fecha;
        this.completa = completa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCompleta() {
        return completa;
    }

    public void setCompleta(int completa) {
        this.completa = completa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(descripcion);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeInt(completa);
    }
}
