package com.example.dam_t3.vistas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.Controlador.SedeController;
import com.example.dam_t3.Modelo.Sede;
import com.example.dam_t3.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class sede extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    GoogleMap VistaMap;
    EditText direc, cords;
    Spinner sed;
    Button btnReg;
    SedeController sedeController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sede);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        direc = findViewById(R.id.txtDireccion);
        cords = findViewById(R.id.TxtUbi);
        sed = findViewById(R.id.spSede);

        btnReg = findViewById(R.id.btnReg);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        sedeController = new SedeController(this);
        ArrayList<Sede> listaSedes = sedeController.MostrarSede();

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
        sed.setAdapter(adapter);

        sed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sede sedeSeleccionada = listaSedes.get(position);
                // Mover el mapa a la ubicación de la sede seleccionada
                direc.setText(sedeSeleccionada.getDireccion());
                cords.setText(sedeSeleccionada.getLatitud() + "," + sedeSeleccionada.getLongitud());

                if (VistaMap != null) {
                    LatLng ubicacion = new LatLng(sedeSeleccionada.getLatitud(), sedeSeleccionada.getLongitud());
                    VistaMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
                    VistaMap.addMarker(new MarkerOptions()
                            .position(ubicacion)
                            .title(sedeSeleccionada.getNombreSede())
                            .snippet(sedeSeleccionada.getDireccion()));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        btnReg.setOnClickListener(v -> finish());
    }
    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        VistaMap=googleMap;

        this.VistaMap.setOnMapClickListener(this);

        this.VistaMap.setOnMapLongClickListener(this);
    }
}