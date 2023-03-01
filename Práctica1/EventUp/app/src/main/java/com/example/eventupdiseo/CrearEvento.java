package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class CrearEvento extends AppCompatActivity{

    private TextView titlepage, addtitulo, addfecha, addhora, addaforo,
            addubicacion, addidioma, addgrado;
    private EditText titulo, fecha, hora, aforo, ubicacion, idioma, grado;
    private ImageButton button_back_crear_evento;
    private Button button_añadir_evento;
    private LinearLayout linearLayout;
    String user_usuario, user_nombre, user_grado, user_foto;

    // Sensor de giroscopio
    SensorManager sensorManagerG;
    Sensor sensorG;
    private Boolean sensorDisponibleG;
    SensorEventListener sensorgiroscop;
    private int contP = 0;
    // Para controlar la diferencia de tiempo en el movimiento
    private long last_updateP = 0;
    private  long current_timeP = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

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
        button_añadir_evento = findViewById(R.id.button_añadir_evento);
        button_back_crear_evento = findViewById(R.id.button_crear_evento);


        // Sensor Giroscopio para crear un nuevo Evento
        // Creamos un objeto SensorManager
        sensorManagerG = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Creamos un objeto sensor para el sensor giroscopio
        sensorG = sensorManagerG.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Comprobamos si existe el sensor en nuestro teléfono
        if (sensorG != null) {
            sensorDisponibleG = true;
        } else {
            sensorDisponibleG = false;
            Toast.makeText(this, "El dispositivo no tiene giroscopio", Toast.LENGTH_LONG).show();
            finish();
        }


        sensorgiroscop = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];

                if (x < -2f && contP == 0){
                    contP++;
                    last_updateP = event.timestamp;
                }

                if(x > 2f && contP == 1){
                    current_timeP = event.timestamp;
                    // Comprobamos que la diferencia de tiempo de los 2 movimientos es corta
                    if (current_timeP - last_updateP < 300000000) {
                        contP++;
                        button_añadir_evento.performClick();
                    }else{
                        contP = 0;
                        last_updateP = 0;
                        current_timeP = 0;
                    }
                }

                // Si hacemos toda la trnasición ponemos el contador a 0
                if (contP == 2){
                    contP = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        // Botón para volver al menu principal
        button_back_crear_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Botón para añadir un evento
        button_añadir_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearEvento(view);

                Intent volver = new Intent(CrearEvento.this, MenuActivity.class);

                volver.putExtra("usuario",user_usuario);
                volver.putExtra("nombre",user_nombre);
                volver.putExtra("foto",user_foto);
                volver.putExtra("grado",user_grado);

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

    private Boolean validateTitle() {
        String campo = titulo.getText().toString();

        if (campo.isEmpty()) {
            titulo.setError("Este campo es obligatorio");
            return false;
        }
        else {
            titulo.setError(null);
            return true;
        }
    }

    private Boolean validateFecha() {
        String campo = fecha.getText().toString();

        if (campo.isEmpty()) {
            fecha.setError("Este campo es obligatorio");
            return false;
        }
        else {
            fecha.setError(null);
            return true;
        }
    }

    private Boolean validateHora() {
        String campo = hora.getText().toString();

        if (campo.isEmpty()) {
            hora.setError("Este campo es obligatorio");
            return false;
        }
        else {
            hora.setError(null);
            return true;
        }
    }

    private Boolean validateUbicacion() {
        String campo = ubicacion.getText().toString();

        if (campo.isEmpty()) {
            ubicacion.setError("Este campo es obligatorio");
            return false;
        }
        else {
            ubicacion.setError(null);
            return true;
        }
    }

    public void crearEvento(View view) {

        // Comprobamos si ha introducido nombre, fecha, hora y ubicación válidos
        if (!validateTitle() || !validateFecha() || !validateHora() || !validateUbicacion()){
            Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            datosCrearEventoCorrectos();
        }
    }

    private void datosCrearEventoCorrectos() {

        // Creamos un objeto de la clase DBHelper
        DBHelper admin = new DBHelper(this);

        // Abrimos la base de datos como escritura
        SQLiteDatabase db = admin.getWritableDatabase();

        // Creamos dos variables string con los datos ingresados por los usuarios
        String evTituloIntroducido = titulo.getText().toString();
        String evFechaIntroducida = fecha.getText().toString();
        String evHoraIntroducida = hora.getText().toString();
        Integer evAforoIntroducido = 100;
        if (!aforo.getText().toString().isEmpty()){
            evAforoIntroducido = Integer.parseInt(aforo.getText().toString());
        }
        String evGradoIntroducido = grado.getText().toString();
        String evUbicacionIntroducida = ubicacion.getText().toString();
        String evIdiomaIntroducido = idioma.getText().toString();

        // Inicializamos al cursor y llamamos al objeto de la base de datos
        Cursor fila = db.rawQuery("select EVE_NOMBRE from EVENTO where EVE_NOMBRE='"
                +evTituloIntroducido+"'",null);

        // Hacemos un try catch para capturar los errores
        try {
            // Si el cursor tiene algún dato
            if (fila.moveToFirst()) {
                String nombre = fila.getString(0);

                if (nombre.equals(evTituloIntroducido)) {
                    Toast.makeText(this, "Ya existe un evento con ese nombre", Toast.LENGTH_LONG).show();
                }
            }
            else{
                admin.crearEvento(evTituloIntroducido,evFechaIntroducida,evHoraIntroducida,evAforoIntroducido,
                        evGradoIntroducido,evUbicacionIntroducida,evIdiomaIntroducido);

                admin.asignarEvento(user_usuario,evTituloIntroducido);
                Toast.makeText(CrearEvento.this, "Listo, evento creado con éxito", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
        }

        db.close();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (sensorDisponibleG) {
            sensorManagerG.registerListener(sensorgiroscop, sensorG, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorDisponibleG) {
            sensorManagerG.unregisterListener(sensorgiroscop);
        }
    }
}