package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class Tui extends AppCompatActivity {

    private TextView text_nombre;
    private ImageButton button_back_tui;
    private ImageView image_foto;
    private Toolbar toolbar;
    String user_usuario, user_nombre, user_grado, user_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui);

        // Vemos la información del usuario
        showAllUserData();

        button_back_tui = findViewById(R.id.button_back_tui);
        toolbar = findViewById(R.id.toolbar_tui);
        image_foto = (ImageView) findViewById(R.id.foto_tui);
        text_nombre = (TextView) findViewById(R.id.texto_nombre_tui);

        // Obtenemos la foto
        int resId = getResources().getIdentifier(user_foto,"drawable",this.getPackageName());
        Drawable drawable = this.getResources().getDrawable(resId);

        // Le asignamos la información a la vista
        image_foto.setImageDrawable(drawable);
        text_nombre.setText(user_nombre);
        toolbar.setTitle("");
        setActionBar(toolbar);

        button_back_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAllUserData() {
        Intent intent = this.getIntent();
        user_usuario = intent.getStringExtra("usuario");
        user_nombre = intent.getStringExtra("nombre");
        user_foto = intent.getStringExtra("foto");
        user_grado = intent.getStringExtra("grado");
    }


}