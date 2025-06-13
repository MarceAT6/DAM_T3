package com.example.dam_t3.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dam_t3.DAO.UPN_DB;
import com.example.dam_t3.Modelo.Alumno;

public class AlumnoController extends UPN_DB {

    Context context;
    public AlumnoController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarAlumno(Alumno dato){
        UPN_DB x = new AlumnoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO Alumno (dni, nombre, apellido, contrasenia, rol, imagen) VALUES( "+
                    "'" + dato.getDni() + "', " +
                    "'" + dato.getNombre() + "', " +
                    "'" + dato.getApellido() + "', " +
                    "'" + dato.getContrasenia() + "', " +
                    "'" + dato.getRol() + "', " +
                    "'" + dato.getImagen() +  "')"
            );
            database.close();
        }
    }

    public boolean SesionAlumno(String dni, String contrasenia){
        UPN_DB x = new AlumnoController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        boolean resultado = false;

        if (database != null) {
            // Consulta con parámetros para evitar inyección SQL
            Cursor cursor = database.rawQuery(
                    "SELECT * FROM Alumno WHERE dni = ? AND contrasenia = ?",
                    new String[]{dni, contrasenia}
            );

            if (cursor != null && cursor.moveToFirst()) {
                resultado = true; // Hay coincidencia → sesión válida
            }

            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }

        return resultado;
    }

    public Alumno MostrarAlumno (String dni){
        UPN_DB x = new AlumnoController(context);
        SQLiteDatabase db = getReadableDatabase();
        Alumno alumno = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Alumno WHERE dni = ?", new String[]{dni});
        if (cursor != null && cursor.moveToFirst()) {
            alumno = new Alumno(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
        }
        db.close();
        return alumno;
    }

    public Alumno MostrarAlumnoId (int id){
        UPN_DB x = new AlumnoController(context);
        SQLiteDatabase db = getReadableDatabase();
        Alumno alumno = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Alumno WHERE id_alumno = ?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            alumno = new Alumno(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
        }
        db.close();
        return alumno;
    }
}
