package com.example.dam_t3.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dam_t3.DAO.UPN_DB;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Asistencia;

import java.util.ArrayList;

public class AsistenciaController extends UPN_DB {
    Context context;
    public AsistenciaController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarAsistencia(Asistencia dato){
        UPN_DB x = new AsistenciaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO Asistencia (id_alumno, id_sede, fecha, hora_entrada, hora_salida) VALUES( "+
                    "'" + dato.getIdAlumno() + "', " +
                    "'" + dato.getIdSede() + "', " +
                    "'" + dato.getFecha() + "', " +
                    "'" + dato.getHoraEnt() + "', " +
                    "'" + dato.getHoraSal() + "')"
            );
            database.close();
        }
    }

    public Asistencia RecuperarAsistencia(int id_alum){
        UPN_DB x = new AsistenciaController(context);
        SQLiteDatabase db = getReadableDatabase();
        Asistencia asis = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Asistencia WHERE id_alumno = ? ORDER BY id_asistencia DESC LIMIT 1", new String[]{String.valueOf(id_alum)});
        if (cursor != null && cursor.moveToFirst()) {
            asis = new Asistencia(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
        }
        db.close();
        return asis;
    }

    public void ActualizarAsistencia(int id_asis, String horaSalida) {
        UPN_DB x = new AsistenciaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null) {
            ContentValues valores = new ContentValues();
            valores.put("hora_salida", horaSalida);

            database.update("Asistencia", valores, "id_asistencia = ?", new String[]{String.valueOf(id_asis)});
            database.close();
        }
    }

    public ArrayList<Asistencia> MostrarAsistencia(int id){
        UPN_DB x = new AsistenciaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Asistencia> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM Asistencia WHERE id_alumno = " + id, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Asistencia (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Asistencia> MostrarAsistenciaCompleta(){
        UPN_DB x = new AsistenciaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Asistencia> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM Asistencia", null);

        if (act.moveToFirst()){
            do{
                datos.add(new Asistencia (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(1)),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
