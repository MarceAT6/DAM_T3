package com.example.dam_t3.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dam_t3.DAO.UPN_DB;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Sede;

import java.util.ArrayList;

public class SedeController extends UPN_DB {
    Context context;
    public SedeController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public ArrayList<Sede> MostrarSede(){
        UPN_DB x = new SedeController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Sede> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM Sede", null);

        if (act.moveToFirst()){
            do{
                datos.add(new Sede (Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2),
                        Double.parseDouble(act.getString(3)),
                        Double.parseDouble(act.getString(4))
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public Sede MostrarSedeId (int id){
        UPN_DB x = new SedeController(context);
        SQLiteDatabase db = getReadableDatabase();
        Sede sede = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Sede WHERE id_sede = ?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            sede = new Sede(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)),
                    Double.parseDouble(cursor.getString(4))
            );
            cursor.close();
        }
        db.close();
        return sede;
    }
}
