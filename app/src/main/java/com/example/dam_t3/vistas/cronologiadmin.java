package com.example.dam_t3.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.Controlador.AlumnoController;
import com.example.dam_t3.Controlador.AsistenciaController;
import com.example.dam_t3.Controlador.SedeController;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Asistencia;
import com.example.dam_t3.Modelo.Sede;
import com.example.dam_t3.R;

import java.util.ArrayList;

public class cronologiadmin extends AppCompatActivity {

    private AsistenciaController act = new AsistenciaController(this);
    private AlumnoController ect = new AlumnoController(this);
    private SedeController ict = new SedeController(this);
    ListView listacronoadmin;
    Button btnregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cronologiadmin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listacronoadmin = findViewById(R.id.lst_crono_admin);
        btnregresar = findViewById(R.id.btn_regresar_crono);
        cargarListaAsistencia();
        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });

    }


    private void cargarListaAsistencia() {
        ArrayList<Asistencia> lista = act.MostrarAsistenciaCompleta();

        ArrayAdapter<Asistencia> adaptador = new ArrayAdapter<Asistencia>(this, R.layout.item_asistenciadmin, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_asistenciadmin, parent, false);

                Asistencia asistencia = getItem(position);
                Alumno alumno = ect.MostrarAlumnoId(asistencia.getIdAlumno());
                Sede sede = ict.MostrarSedeId(asistencia.getIdSede());

                TextView AsisPrincAdmin = itemView.findViewById(R.id.txt_AsisPrincAdmin);
                TextView AsisSecAdmin = itemView.findViewById(R.id.txt_AsisSecAdmin);

                AsisPrincAdmin.setText("Nombre del Alumno: "+ alumno.getNombre() + " " + alumno.getApellido() + "\nFecha: " + asistencia.getFecha());
                AsisSecAdmin.setText("Sede: "+ sede.getNombreSede() + "\nHora de Entrada " + asistencia.getHoraEnt() + "\nHora de Salida: " + asistencia.getHoraSal());

                return itemView;
            }
        };
        listacronoadmin.setAdapter(adaptador);
    }
}