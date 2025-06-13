package com.example.dam_t3.vistas;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.Controlador.AlumnoController;
import com.example.dam_t3.Controlador.AsistenciaController;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Asistencia;
import com.example.dam_t3.R;

import com.bumptech.glide.Glide;

public class perestudiante extends AppCompatActivity {

    TextView nombre, apellido, dni, dni2, txtasistencia;
    Button btnregresar;
    ImageView imgalumno;

    private AlumnoController act = new AlumnoController(this);

    private AsistenciaController ect = new AsistenciaController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perestudiante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.txt_nombre_perfil);
        apellido = findViewById(R.id.txt_apellido_perfil);
        dni = findViewById(R.id.txt_dni_perfil);
        txtasistencia = findViewById(R.id.txt_asistencia_perfil);

        btnregresar = findViewById(R.id.btn_regresar_perfil);

        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });

        String dni = getIntent().getStringExtra("dni_usuario");

        cargarEstudiante(dni);
    }

    public void cargarEstudiante(String dni){
        Alumno alumno = act.MostrarAlumno(dni);

        Asistencia asistencia = ect.RecuperarAsistencia(alumno.getIdAlumno());

        if (alumno != null){
            nombre = findViewById(R.id.txt_nombre_perfil);
            apellido = findViewById(R.id.txt_apellido_perfil);
            dni2 = findViewById(R.id.txt_dni_perfil);
            imgalumno = findViewById(R.id.img_alumno);
            txtasistencia = findViewById(R.id.txt_asistencia_perfil);

            nombre.setText("Nombre: " + alumno.getNombre());
            apellido.setText("Apellido: " + alumno.getApellido());
            dni2.setText("DNI: " + alumno.getDni());
            txtasistencia.setText("Ultima asistencia: " + asistencia.getHoraEnt() + " - " + asistencia.getHoraSal());

            Glide.with(this)
                    .load(alumno.getImagen())
                    .into(imgalumno);

        } else {
            Toast.makeText(this, "No se encontró el alumno", Toast.LENGTH_SHORT).show();
        }
    }
}