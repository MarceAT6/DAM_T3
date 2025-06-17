package com.example.dam_t3.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class registrarentrada extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    GoogleMap mMap;
    Spinner sedes;
    Button btnRegistrar;

    EditText fecha, hora_entrada;

    private SedeController act = new SedeController(this);
    private AlumnoController ect = new AlumnoController(this);
    private AsistenciaController ict = new AsistenciaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarentrada);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sedes = findViewById(R.id.spn_sedes);
        btnRegistrar = findViewById(R.id.btn_registrar_hora);
        fecha = findViewById(R.id.txt_fecha);
        hora_entrada = findViewById(R.id.txt_hora_entrada);



        ArrayList<Sede> listaSedes = act.MostrarSede();

        List<String> nombresSedes = new ArrayList<>();
        for (Sede sede : listaSedes) {
            nombresSedes.add(sede.getNombreSede());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombresSedes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sedes.setAdapter(adapter);

        sedes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sede sedeSeleccionada = listaSedes.get(position);
                // Mover el mapa a la ubicación de la sede seleccionada
                if (mMap != null) {
                    LatLng ubicacion = new LatLng(sedeSeleccionada.getLatitud(), sedeSeleccionada.getLongitud());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
                    mMap.addMarker(new MarkerOptions()
                            .position(ubicacion)
                            .title(sedeSeleccionada.getNombreSede())
                            .snippet(sedeSeleccionada.getDireccion()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String dni = getIntent().getStringExtra("dni_usuario");
                    Alumno alumno = ect.MostrarAlumno(dni);

                    int posicionSeleccionada = sedes.getSelectedItemPosition();
                    Sede sedeSeleccionada = listaSedes.get(posicionSeleccionada);

                    Log.d("DEBUG", "Intentando insertar con categoría_id: " + sedeSeleccionada.getIdSede());

                    int id_alum = alumno.getIdAlumno();
                    int id_sed = sedeSeleccionada.getIdSede();
                    String fec = fecha.getText().toString().trim();
                    String hor_ent = hora_entrada.getText().toString().trim();

                    if (fec.isEmpty() || hor_ent.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Asistencia dato = new Asistencia(id_sed, id_alum, fec, hor_ent,"");
                    ict.InsertarAsistencia(dato);

                    Toast.makeText(getApplicationContext(), "Asistencia registrada correctamente", Toast.LENGTH_SHORT).show();

                    fecha.setText("");
                    hora_entrada.setText("");

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al registrar asistencia: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Desactivar temporalmente el TextWatcher
                fecha.removeTextChangedListener(this);

                String f = s.toString(); // Convertimos a String

                // Si la fecha tiene más de 10 caracteres, cortamos el texto
                if (f.length() > 10) {
                    f = f.substring(0, 10);
                }

                // Insertamos "/" después del día y mes
                if (f.length() > 2 && f.charAt(2) != '/') {
                    f = f.substring(0, 2) + "/" + f.substring(2);
                }
                if (f.length() > 5 && f.charAt(5) != '/') {
                    f = f.substring(0, 5) + "/" + f.substring(5);
                }

                // Establecemos el texto formateado
                fecha.setText(f);
                fecha.setSelection(fecha.length()); // Para que el cursor esté al final

                // Volver a activar el TextWatcher
                fecha.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        hora_entrada.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String hora = s.toString();

                // Si el texto tiene más de 5 caracteres, lo cortamos (porque es HH:mm)
                if (hora.length() > 5) {
                    hora = hora.substring(0, 5);
                }

                // Si la longitud es mayor a 2 (HH), insertar ":" después de la hora
                if (hora.length() > 2 && hora.charAt(2) != ':') {
                    hora = hora.substring(0, 2) + ":" + hora.substring(2);
                }

                // Establecer el texto formateado en el EditText
                hora_entrada.setText(hora);

                // Poner el cursor al final del texto
                hora_entrada.setSelection(hora.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;

        this.mMap.setOnMapClickListener(this);

        this.mMap.setOnMapLongClickListener(this);
    }


}