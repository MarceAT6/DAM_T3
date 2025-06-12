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

import com.bumptech.glide.Glide;
import com.example.dam_t3.Controlador.AlumnoController;
import com.example.dam_t3.Controlador.AsistenciaController;
import com.example.dam_t3.Controlador.SedeController;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Asistencia;
import com.example.dam_t3.Modelo.Sede;
import com.example.dam_t3.R;

public class registrasalida extends AppCompatActivity {

    EditText nomape, sede, entrada,salida, fecha;

    Button btnSalida;
    private AsistenciaController act = new AsistenciaController(this);
    private AlumnoController ect = new AlumnoController(this);
    private SedeController ict = new SedeController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrasalida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomape = findViewById(R.id.txt_alumno_salida);
        sede = findViewById(R.id.txt_sede_salida);
        entrada = findViewById(R.id.txt_hora_entrada_sal);
        salida = findViewById(R.id.txt_hora_salida);
        fecha =  findViewById(R.id.txt_fecha);

        btnSalida = findViewById(R.id.btn_registrar_salida);

        String dni = getIntent().getStringExtra("dni_usuario");
        int id_cam = cargarAsistencia(dni);

        btnSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String sal = salida.getText().toString();

                    if (sal.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    act.ActualizarAsistencia(id_cam, sal);

                    Toast.makeText(getApplicationContext(), "Asistencia registrada correctamente", Toast.LENGTH_SHORT).show();

                    salida.setText("");
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public int cargarAsistencia(String dni){

        int id_asis = 0;
        Alumno alumno = ect.MostrarAlumno(dni);
        int id = alumno.getIdAlumno();

        Asistencia asistencia = act.RecuperarAsistencia(id);

        Sede local = ict.MostrarSede(asistencia.getIdSede());

        if (asistencia != null){

            id_asis = asistencia.getIdAsistencia();

            nomape = findViewById(R.id.txt_alumno_salida);
            sede = findViewById(R.id.txt_sede_salida);
            entrada = findViewById(R.id.txt_hora_entrada_sal);
            fecha =  findViewById(R.id.txt_fecha);

            nomape.setText(alumno.getNombre()+ " " +alumno.getApellido());
            sede.setText(local.getNombreSede());
            entrada.setText(asistencia.getHoraEnt());
            fecha.setText(asistencia.getFecha());


        } else {
            Toast.makeText(this, "No se encontró el alumno", Toast.LENGTH_SHORT).show();
        }

        return id_asis;
    }
}