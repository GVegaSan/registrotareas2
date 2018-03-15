package com.example.montoya.registrotareas;


public class LogicaBD {

    private final static String NOMBRE_TABLA="tbltareas";
    private final static String ID="id";
    private final static String DESCRIPCION="descripcion";
    private final static String FECHA="fecha";
    private final static String HORA="hora";
    private final static String COMPLETA="completa";

    public static  final String CREARTABLATAREAS = "create table "+NOMBRE_TABLA+"("
            +ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +DESCRIPCION+" TEXT NOT NULL, "
            +FECHA+" TEXT, "
            +HORA+" TEXT, "
            +COMPLETA+" INTEGER)";

    public static String insertarTarea(String descripcion, String fecha, String hora, int completa){
        String consulta = "insert into "+NOMBRE_TABLA+"("
                +DESCRIPCION+","
                +FECHA+","
                +HORA+","
                +COMPLETA+")"
                +" values('"+descripcion+"','"+fecha+"','"+hora+"','"+0+"');";
        return consulta;
    }
    public static String editarTarea(String descripcion, String fecha, String hora, int id, int completa){
        String consulta = "UPDATE tbltareas SET descripcion = '" + descripcion + "', fecha = '" + fecha + "', hora = '" + hora + "', completa = '" + completa + "' WHERE id = " + id;
        return consulta;
    }

    public static String buscar(int id){
        String consulta = "SELECT * FROM tbltareas WHERE id = " + id;
        return consulta;
    }

    public static String eliminarTarea(int id){
        String consulta = "DELETE FROM tbltareas WHERE id = " + id;
        return consulta;
    }

}
