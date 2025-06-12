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
                "rol TEXT," +
                "imagen TEXT" +
                ")");

        // Tabla Sede
        db.execSQL("CREATE TABLE Sede (" +
                "id_sede INTEGER PRIMARY KEY," +
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
                "fecha TEXT," +
                "hora_entrada TEXT," +
                "hora_salida TEXT," +
                "FOREIGN KEY(id_alumno) REFERENCES Alumno(id_alumno)," +
                "FOREIGN KEY(id_sede) REFERENCES Sede(id_sede)" +
                ")");

        db.execSQL("INSERT INTO Alumno (dni, nombre, apellido, contrasenia, rol, imagen) " +
                "VALUES ('11111111', 'Admin', 'Admin', 'Admin123', 'Administrador', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy7nFdX1g_CVR4WyP5LgKOGytP0J8PE53_RQ&s')");

        db.execSQL("INSERT INTO Sede (id_sede, nombre, direccion, latitud, longitud) " +
                "VALUES (1, 'UNIVERSIDAD PRIVADA DEL NORTE - ATE', 'Av. Nicolás Ayllón 3720, Ate', -12.056195451708145, -76.96690458745758)," +
                "(2, 'UNIVERSIDAD PRIVADA DEL NORTE - LOS OLIVOS', 'Av. Alfredo Mendiola 6062, Los Olivos', -11.959274254590996, -77.068022027583)," +
                "(3, 'UNIVERSIDAD PRIVADA DEL NORTE - BREÑA', 'Av. Tingo María 1122, Cercado de Lima', -12.05841773401743, -77.05876504214507)," +
                "(4, 'UNIVERSIDAD PRIVADA DEL NORTE - SJL', 'Av. El Sol 461 San Juan de Lurigancho', -11.984906853634644, -77.00499122496672)," +
                "(5, 'UNIVERSIDAD PRIVADA DEL NORTE - COMAS', 'Av. Andrés Belaunde cdra. 10 s/n, Comas', -11.933827399744402, -77.05850130230112)," +
                "(6, 'UNIVERSIDAD PRIVADA DEL NORTE - CHORRILLOS', 'Av. Guardia Peruana 890, Chorrillos', -12.179370160007773, -77.0034322951738)"
        );
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
