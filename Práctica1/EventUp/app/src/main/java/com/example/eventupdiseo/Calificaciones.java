package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Calificaciones extends AppCompatActivity {

    String user_usuario, user_nombre, user_grado, user_foto;
    private TextView text_nombre, text_grado;
    private ImageButton button_back_calificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificaciones);

        button_back_calificaciones = findViewById(R.id.button_back_calificaciones);

        // Vemos la información del usuario
        showAllUserData();

        button_back_calificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showAllUserData() {
        Intent intent = this.getIntent();
        user_usuario = intent.getStringExtra("usuario");
        user_nombre = intent.getStringExtra("nombre");
        user_foto = intent.getStringExtra("foto");
        user_grado = intent.getStringExtra("grado");
    }
}