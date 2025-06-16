package com.example.dam_t3.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dam_t3.R;

public class registrarhora extends AppCompatActivity {

    Button entrada, salida, btnregresar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarhora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        entrada = findViewById(R.id.btn_entrada);
        salida = findViewById(R.id.btn_salida);
        btnregresar = findViewById(R.id.btn_regresar_salida);

        salida.setAlpha(0.5f);
        salida.setEnabled(false);

        String dni = getIntent().getStringExtra("dni_usuario");

        entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registrarhora.this, registrarentrada.class);
                intent.putExtra("dni_usuario", dni);
                startActivityForResult(intent, 100);
            }
        });

        salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registrarhora.this, registrasalida.class);
                intent.putExtra("dni_usuario", dni);
                startActivityForResult(intent, 200);
            }
        });
        btnregresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { finish(); }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // Aquí deshabilitas el botón
            Button miBoton = findViewById(R.id.btn_entrada);
            salida.setEnabled(true); // para volver a habilitar
            salida.setAlpha(1.0f);
            miBoton.setAlpha(0.5f);
            miBoton.setEnabled(false);
        }
        if (requestCode == 200 && resultCode == RESULT_OK) {
            entrada.setEnabled(true); // para volver a habilitar
            entrada.setAlpha(1.0f);
            salida.setAlpha(0.5f);
            salida.setEnabled(false);
        }
    }

}