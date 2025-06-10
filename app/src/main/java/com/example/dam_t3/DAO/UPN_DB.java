package com.example.dam_t3.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UPN_DB extends SQLiteOpenHelper {
    public static final String DB_NAME = "upn_db.db";
    public static final int DB_VERSION = 1;

    public UPN_DB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Alumno (" +
                "id_alumno INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT UNIQUE NOT NULL," +
                "nombre TEXT," +
                "apellido TEXT," +
                "contrasenia TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Alumno");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
