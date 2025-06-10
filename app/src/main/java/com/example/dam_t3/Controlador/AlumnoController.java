package com.example.dam_t3.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dam_t3.DAO.UPN_DB;
import com.example.dam_t3.Modelo.Alumno;

import java.util.ArrayList;

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
            database.execSQL("INSERT INTO Alumno (dni, nombre, apellido, contrasenia) VALUES( "+
                    "'" + dato.getDni() + "', " +
                    "'" + dato.getNombre() + "', " +
                    "'" + dato.getApellido() + "', " +
                    "'" + dato.getContrasenia() +  "')"
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
}
