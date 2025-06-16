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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.Controlador.AlumnoController;
import com.example.dam_t3.Controlador.AsistenciaController;
import com.example.dam_t3.Controlador.CursoController;
import com.example.dam_t3.Modelo.Alumno;
import com.example.dam_t3.Modelo.Asistencia;
import com.example.dam_t3.Modelo.Curso;
import com.example.dam_t3.Modelo.Sede;
import com.example.dam_t3.R;

import java.util.ArrayList;

public class regcurso extends AppCompatActivity {

    private CursoController act = new CursoController(this);


    private Button btnregistrar, btnregresar, btneliminar;

    private EditText nombre;

    ListView listview_regcursos;

    private ArrayList<Curso> listaCursos; // lista actual de cursos
    int itemSeleccionado = -1; // para recordar el índice

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regcurso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        listview_regcursos = findViewById(R.id.lv_regcursos);

        nombre = findViewById(R.id.txt_nombrecurso);

        btnregresar = findViewById(R.id.btn_regresarcurso);
        btnregistrar = findViewById(R.id.btn_registrarcurso);
        btneliminar = findViewById(R.id.btn_eliminarcurso);

        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {

                    String nom = nombre.getText().toString();


                    if (nom.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Curso dato = new Curso(nom);
                    act.InsertarCurso(dato);

                    Toast.makeText(getApplicationContext(), "Curso agregado correctamente", Toast.LENGTH_SHORT).show();

                    nombre.setText("");
                    cargarListaCursos();


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error al insertar curso: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        btneliminar.setOnClickListener(view -> {
            if (itemSeleccionado != -1 && itemSeleccionado < listaCursos.size()) {
                Curso cursoAEliminar = listaCursos.get(itemSeleccionado);

                act.EliminarCurso(cursoAEliminar.getIdCurso());

                Toast.makeText(getApplicationContext(), "Curso eliminado", Toast.LENGTH_SHORT).show();

                nombre.setText("");
                itemSeleccionado = -1;
                cargarListaCursos();
            } else {
                Toast.makeText(getApplicationContext(), "Selecciona un curso para eliminar", Toast.LENGTH_SHORT).show();
            }
        });


        cargarListaCursos();
    }

    private void cargarListaCursos() {
        listaCursos = act.MostrarCursosCompletos(); // obtener lista actualizada

        ArrayAdapter<Curso> adaptador = new ArrayAdapter<Curso>(this, android.R.layout.simple_list_item_1, listaCursos) {

        };
        listview_regcursos.setAdapter(adaptador);

        listview_regcursos.setOnItemClickListener((parent, view, position, id) -> {
            Curso cursoSeleccionado = listaCursos.get(position);
            nombre.setText(cursoSeleccionado.getNombre());
            itemSeleccionado = position;
        });


    }
}