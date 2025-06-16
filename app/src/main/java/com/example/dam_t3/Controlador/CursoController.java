package com.example.dam_t3.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dam_t3.DAO.UPN_DB;
import com.example.dam_t3.Modelo.Curso;

import java.util.ArrayList;

public class CursoController extends UPN_DB {

    Context context;
    public CursoController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarCurso(Curso dato){
        UPN_DB x = new CursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO Curso (nombre) VALUES( "+
                    "'" + dato.getNombre() +  "')"
            );
            database.close();
        }
    }

    public void EliminarCurso(int idCurso) {
        UPN_DB x = new CursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null) {
            database.execSQL("DELETE FROM Curso WHERE id_curso = " + idCurso);
            database.close();
        }
    }

    public ArrayList<Curso> MostrarCursosCompletos(){
        UPN_DB x = new CursoController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Curso> datos = new ArrayList<>();
        Cursor act = database.rawQuery("SELECT id_curso, nombre FROM Curso", null); // <--- ahora sí trae el ID

        if (act.moveToFirst()){
            do{
                int id = act.getInt(0);              // ID de la BD
                String nombre = act.getString(1);    // Nombre del curso
                datos.add(new Curso(id, nombre));

            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public Curso MostrarCursoId (int id){
        UPN_DB x = new CursoController(context);
        SQLiteDatabase db = getReadableDatabase();
        Curso curso = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Curso WHERE id_curso = ?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            curso = new Curso(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1)
            );
            cursor.close();
        }
        db.close();
        return curso;
    }


}
