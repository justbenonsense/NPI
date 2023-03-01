package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import Database.DBHelper;

public class FilterActivity extends AppCompatActivity {

    private TextView titlepage, addtitulo, addfecha, addhora, addaforo,
            addubicacion, addidioma, addgrado;
    private EditText titulo, fecha, hora, aforo, ubicacion, idioma, grado;
    private ImageButton button_back_filtrar_evento;
    private Button button_filtrar_evento;
    private LinearLayout linearLayout;
    String user_usuario, user_nombre, user_grado, user_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Vemos la información del usuario
        showAllUserData();

        // Unimos con la parte visible
        titlepage = (TextView) findViewById(R.id.texto_crear_evento);
        addtitulo = (TextView) findViewById(R.id.add_event_title);
        addfecha = (TextView) findViewById(R.id.add_event_fecha);
        addhora = (TextView) findViewById(R.id.add_event_hora);
        addaforo = (TextView) findViewById(R.id.add_event_aforo);
        addubicacion = (TextView) findViewById(R.id.add_event_ubi);
        addgrado = (TextView) findViewById(R.id.add_event_grado);
        addidioma = (TextView) findViewById(R.id.add_event_idioma);
        titulo = (EditText) findViewById(R.id.event_title_added);
        fecha = (EditText) findViewById(R.id.event_fecha_added);
        hora = (EditText) findViewById(R.id.event_hora_added);
        aforo = (EditText) findViewById(R.id.event_aforo_added);
        ubicacion = (EditText) findViewById(R.id.event_ubi_added);
        grado = (EditText) findViewById(R.id.event_grado_added);
        idioma = (EditText) findViewById(R.id.event_idioma_added);
        button_filtrar_evento = findViewById(R.id.button_filtrar_evento);
        button_back_filtrar_evento = findViewById(R.id.button_back_filtros);

        // Botón para volver al menu principal
        button_back_filtrar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Botón para filtrar los eventos
        button_filtrar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creamos un objeto de la clase DBHelper
                DBHelper admin = new DBHelper(view.getContext());

                // Abrimos la base de datos como escritura
                SQLiteDatabase db = admin.getWritableDatabase();

                // Creamos dos variables string con los datos ingresados por los usuarios
                String evTituloIntroducido = titulo.getText().toString();
                String evFechaIntroducida = fecha.getText().toString();
                String evHoraIntroducida = hora.getText().toString();
                String evGradoIntroducido = grado.getText().toString();
                String evUbicacionIntroducida = ubicacion.getText().toString();
                String evIdiomaIntroducido = idioma.getText().toString();
                Integer evAforoIntroducido = -1;

                try {
                    evAforoIntroducido = Integer.parseInt(aforo.getText().toString());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                Intent volver = new Intent(FilterActivity.this, MenuActivity.class);

                volver.putExtra("usuario",user_usuario);
                volver.putExtra("nombre",user_nombre);
                volver.putExtra("foto",user_foto);
                volver.putExtra("grado",user_grado);
                volver.putExtra("tituloeventofiltro",evTituloIntroducido);
                volver.putExtra("fechaeventofiltro",evFechaIntroducida);
                volver.putExtra("horaeventofiltro",evHoraIntroducida);
                volver.putExtra("gradoeventofiltro",evGradoIntroducido);
                volver.putExtra("ubicacioneventofiltro",evUbicacionIntroducida);
                volver.putExtra("idiomaeventofiltro",evIdiomaIntroducido);
                volver.putExtra("aforoeventofiltro",evAforoIntroducido);

                startActivity(volver);
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