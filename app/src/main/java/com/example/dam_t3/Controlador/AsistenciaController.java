package com.example.dam_t3.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dam_t3.DAO.UPN_DB;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Asistencia;

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
}
