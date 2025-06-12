package com.example.dam_t3.vistas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.dam_t3.Controlador.AlumnoController;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.R;

public class logestudiante extends AppCompatActivity {

    Button btningresar, btnregresar;
    EditText txtdni, txtcontrasenia;

    public String dniof;

    private AlumnoController act = new AlumnoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logestudiante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtdni = findViewById(R.id.txt_dni_sesion);
        txtcontrasenia = findViewById(R.id.txt_contrasenia_sesion);

        btningresar = findViewById(R.id.btn_ingresar_sesion);
        btnregresar = findViewById(R.id.btn_regresar_sesion);

        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });

        btningresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try
                {
                    boolean bool;
                    String dni = txtdni.getText().toString();
                    String contrasenia = txtcontrasenia.getText().toString();

                    if (dni.isEmpty() || contrasenia.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    bool = act.SesionAlumno(dni, contrasenia);

                    if (bool){
                        String rol = ObtenerRol(dni);

                        if (rol.equals("Estudiante")) {
                            Toast.makeText(getApplicationContext(), "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(logestudiante.this, menuestudiante.class);
                            intent.putExtra("dni_usuario", dni);
                            startActivity(intent);
                        }
                        else if(rol.equals("Administrador")){
                            Toast.makeText(getApplicationContext(), "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(logestudiante.this, menuadministrador.class);
                            intent.putExtra("dni_usuario", dni);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Sucedio un Error, Intente mas tarde", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Dni o Contraseña Incorrecta. Intente de nuevo", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error de sistema: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

    }

    public String ObtenerRol(String dni){
        Alumno alumno = act.MostrarAlumno(dni);
        String rol = "";
        if (alumno != null){
            rol = alumno.getRol();
        } else {
            Toast.makeText(this, "No se encontró el alumno", Toast.LENGTH_SHORT).show();
        }
        return rol;
    }
}