package com.example.dam_t3.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.R;

public class menuestudiante extends AppCompatActivity {

    Button btnperfil, btnhora, btncrono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menuestudiante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnperfil = findViewById(R.id.btn_perfil);
        btnhora = findViewById(R.id.btn_reghor);
        btncrono = findViewById(R.id.btn_crono);

        String dni = getIntent().getStringExtra("dni_usuario");

        btnperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuestudiante.this, perestudiante.class);
                intent.putExtra("dni_usuario", dni);
                startActivity(intent);
            }
        });

        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuestudiante.this, registrarhora.class);
                intent.putExtra("dni_usuario", dni);
                startActivity(intent);
            }
        });

        btncrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuestudiante.this, CronologiaEstudiante.class);
                intent.putExtra("dni_usuario", dni);
                startActivity(intent);
            }
        });
    }
}