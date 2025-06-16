package com.example.dam_t3.vistas;

import android.content.Intent;
import android.hardware.biometrics.PromptContentViewWithMoreOptionsButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.R;

public class menuadministrador extends AppCompatActivity {

    Button btnregistra, btncrono, btnacursos,btnsedes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menuadministrador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnregistra = findViewById(R.id.btn_registro_alumno);
        btncrono = findViewById(R.id.btn_cronologia);
        btnacursos = findViewById(R.id.btn_registro_curso);
        btnsedes = findViewById(R.id.btn_sedes);

        btnregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuadministrador.this, regestudiante.class);
                startActivity(intent);
            }
        });

        btncrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuadministrador.this, cronologiadmin.class);
                startActivity(intent);
            }
        });
        btnacursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menuadministrador.this,regcurso.class);
            }
        });
        btnsedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuadministrador.this, sede.class);
                startActivity(intent);
            }
        });
    }
}