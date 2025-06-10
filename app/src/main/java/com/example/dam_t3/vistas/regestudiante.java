package com.example.dam_t3.vistas;

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

import com.example.dam_t3.Controlador.AlumnoController;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.R;

public class regestudiante extends AppCompatActivity {

    private Button btnregistrar, btnregresar;
    private EditText nombre, apellido, dni, contrasenia;

    private AlumnoController act = new AlumnoController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regestudiante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.txt_nombre);
        apellido = findViewById(R.id.txt_apellido);
        dni = findViewById(R.id.txt_dni);
        contrasenia = findViewById(R.id.txt_contrasenia);

        btnregresar = findViewById(R.id.btn_regresar);
        btnregistrar = findViewById(R.id.btn_registrar);

        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {

                    String nom = nombre.getText().toString();
                    String ape = apellido.getText().toString();
                    String dociden = dni.getText().toString().trim();
                    String contr = contrasenia.getText().toString().trim();

                    if (nom.isEmpty() || ape.isEmpty() || dociden.isEmpty() || contr.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (dociden.length() < 8) {
                        Toast.makeText(getApplicationContext(), "El número de documento debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Alumno dato = new Alumno(dociden, nom, ape, contr);
                    act.InsertarAlumno(dato);

                    Toast.makeText(getApplicationContext(), "Alumno agregado correctamente", Toast.LENGTH_SHORT).show();

                    nombre.setText("");
                    apellido.setText("");
                    dni.setText("");
                    contrasenia.setText("");

                    Intent intent = new Intent(regestudiante.this, logestudiante.class);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error al insertar alumno: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}