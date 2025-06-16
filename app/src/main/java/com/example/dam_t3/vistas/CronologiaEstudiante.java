package com.example.dam_t3.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;

public class CronologiaEstudiante extends AppCompatActivity {

    private AsistenciaController act = new AsistenciaController(this);
    private AlumnoController ect = new AlumnoController(this);

    ListView listacronologia;
    EditText nomape;
    Button btnregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cronologia_estudiante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listacronologia = findViewById(R.id.lst_cronología);
        nomape = findViewById(R.id.txt_nombre_crono);

        String dni = getIntent().getStringExtra("dni_usuario");
        Alumno alumno = ect.MostrarAlumno(dni);

        nomape.setText(alumno.getNombre() + " " + alumno.getApellido());

        cargarListaAsistencia(alumno.getIdAlumno());

        btnregresar = findViewById(R.id.btn_crono_est);

        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });


    }

    private void cargarListaAsistencia(int id) {
        ArrayList<Asistencia> lista = act.MostrarAsistencia(id);

        ArrayAdapter<Asistencia> adaptador = new ArrayAdapter<Asistencia>(this, R.layout.item_asistencia, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_asistencia, parent, false);

                Asistencia asistencia = getItem(position);

                TextView AsisPrinc = itemView.findViewById(R.id.txt_AsisPrincAdmin);
                TextView AsisSec = itemView.findViewById(R.id.txt_AsisSecAdmin);

                AsisPrinc.setText("Fecha: " + asistencia.getFecha());
                AsisSec.setText("Hora de Entrada " + asistencia.getHoraEnt() + "\nHora de Salida: " + asistencia.getHoraSal());

                return itemView;
            }
        };

        listacronologia.setAdapter(adaptador);
    }
}