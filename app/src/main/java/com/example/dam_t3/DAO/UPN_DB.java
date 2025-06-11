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
        //Tabla ALumno
        db.execSQL("CREATE TABLE Alumno (" +
                "id_alumno INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT UNIQUE NOT NULL," +
                "nombre TEXT," +
                "apellido TEXT," +
                "contrasenia TEXT," +
                "imagen TEXT" +
                ")");

        // Tabla Sede
        db.execSQL("CREATE TABLE Sede (" +
                "id_sede INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "direccion TEXT," +
                "latitud REAL," +
                "longitud REAL" +
                ")");

        // Tabla Curso
        db.execSQL("CREATE TABLE Curso (" +
                "id_curso INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL" +
                ")");

        // Tabla Asistencia
        db.execSQL("CREATE TABLE Asistencia (" +
                "id_asistencia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_alumno INTEGER," +
                "id_sede INTEGER," +
                "fecha DATE," +
                "hora_entrada TIME," +
                "hora_salida TIME," +
                "latitud REAL," +
                "longitud REAL," +
                "FOREIGN KEY(id_alumno) REFERENCES Alumno(id_alumno)," +
                "FOREIGN KEY(id_sede) REFERENCES Sede(id_sede)" +
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
