package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import Database.Evento;
import Database.EventoAdapter;

public class EditarEvento extends AppCompatActivity {

    private TextView titlepage, edittitulo, editfecha, edithora, editaforo,
            editubicacion, editidioma, editgrado, titulo;
    private EditText fecha, hora, aforo, ubicacion, idioma, grado;
    private ImageButton button_back_editar_evento;
    private Button button_editar_evento, button_eliminar_evento;
    private LinearLayout linearLayout;
    String user_usuario, user_nombre, user_grado, user_foto;
    String evento_nombre, evento_fecha, evento_hora, evento_ubicacion, evento_grado,evento_idioma, evento_aforo;
    String titulo_anterior;

    //Sensor giroscopio
    private SensorManager sensorManager;
    Sensor sensor;
    private Boolean sensorDispG;
    int cont=0;
    long last_update,current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);

        // Vemos la información del usuario y del evento
        showAllUserData();
        showAllEventData();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //Obtenemos el acceso al giroscopio
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Unimos con la parte visible
        titlepage = (TextView) findViewById(R.id.texto_crear_evento);
        edittitulo = (TextView) findViewById(R.id.edit_event_title);
        editfecha = (TextView) findViewById(R.id.edit_event_fecha);
        edithora = (TextView) findViewById(R.id.edit_event_hora);
        editaforo = (TextView) findViewById(R.id.edit_event_aforo);
        editubicacion = (TextView) findViewById(R.id.edit_event_ubi);
        editgrado = (TextView) findViewById(R.id.edit_event_grado);
        editidioma = (TextView) findViewById(R.id.edit_event_idioma);
        titulo = (TextView) findViewById(R.id.event_title_edited);
        fecha = (EditText) findViewById(R.id.event_fecha_edited);
        hora = (EditText) findViewById(R.id.event_hora_edited);
        aforo = (EditText) findViewById(R.id.event_aforo_edited);
        ubicacion = (EditText) findViewById(R.id.event_ubi_edited);
        grado = (EditText) findViewById(R.id.event_grado_edited);
        idioma = (EditText) findViewById(R.id.event_idioma_edited);
        button_editar_evento = findViewById(R.id.button_editar_evento);
        button_eliminar_evento = findViewById(R.id.button_eliminar_evento);
        button_back_editar_evento = findViewById(R.id.button_back_editar_evento);


        // Asignamos la información al evento
        titulo.setText(evento_nombre);
        fecha.setText(evento_fecha);
        hora.setText(evento_hora);
        aforo.setText(evento_aforo);
        ubicacion.setText(evento_ubicacion);
        grado.setText(evento_grado);
        idioma.setText(evento_idioma);


        // Comprobamos si existe el sensor en nuestro teléfono
        if (sensor != null) {
            sensorDispG = true;
        } else {
            sensorDispG = false;
            Toast.makeText(this, "El dispositivo no tiene giroscopio", Toast.LENGTH_LONG).show();
            finish();
        }


        // Botón para volver al menu principal
        button_back_editar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EditarEvento.this, MenuActivity.class);

                volver.putExtra("usuario",user_usuario);
                volver.putExtra("nombre",user_nombre);
                volver.putExtra("foto",user_foto);
                volver.putExtra("grado",user_grado);

                startActivity(volver);
            }
        });

        // Botón para editar un evento
        button_editar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarEvento(view);

                Intent volver = new Intent(EditarEvento.this, MenuActivity.class);

                volver.putExtra("usuario",user_usuario);
                volver.putExtra("nombre",user_nombre);
                volver.putExtra("foto",user_foto);
                volver.putExtra("grado",user_grado);

                startActivity(volver);
            }
        });

        // Botón para borrar un evento
        button_eliminar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarEvento();

                Intent volver = new Intent(EditarEvento.this, MenuActivity.class);

                volver.putExtra("usuario",user_usuario);
                volver.putExtra("nombre",user_nombre);
                volver.putExtra("foto",user_foto);
                volver.putExtra("grado",user_grado);

                startActivity(volver);
            }
        });

        //Sensores
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
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

    private void showAllEventData() {
        Intent intent = this.getIntent();
        evento_nombre = intent.getStringExtra("eventonombre");
        evento_fecha = intent.getStringExtra("eventofecha");
        evento_hora = intent.getStringExtra("eventohora");
        evento_ubicacion = intent.getStringExtra("eventoubicacion");
        evento_grado = intent.getStringExtra("eventogrado");
        evento_idioma = intent.getStringExtra("eventoidioma");
        evento_aforo = intent.getStringExtra("eventoaforo");
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

    public void editarEvento(View view) {

        // Comprobamos si ha introducido nombre, fecha, hora y ubicación válidos
        if (!validateTitle() || !validateFecha() || !validateHora() || !validateUbicacion()){
            Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            datosEditarEventoCorrectos();
        }
    }


    private void datosEditarEventoCorrectos() {

        // Creamos un objeto de la clase DBHelper
        DBHelper admin = new DBHelper(this);

        // Abrimos la base de datos como escritura
        SQLiteDatabase db = admin.getWritableDatabase();

        // Creamos dos variables string con los datos ingresados por los usuarios
        String evFechaIntroducida = fecha.getText().toString();
        String evHoraIntroducida = hora.getText().toString();
        Integer evAforoIntroducido = Integer.parseInt(aforo.getText().toString());
        String evGradoIntroducido = grado.getText().toString();
        String evUbicacionIntroducida = ubicacion.getText().toString();
        String evIdiomaIntroducido = idioma.getText().toString();


        admin.modificarEvento(evento_nombre,evFechaIntroducida,evHoraIntroducida,evAforoIntroducido,
                evGradoIntroducido,evUbicacionIntroducida,evIdiomaIntroducido);

        db.close();

    }

    public void eliminarEvento() {

        // Creamos un objeto de la clase DBHelper
        DBHelper admin = new DBHelper(this);

        // Abrimos la base de datos como escritura
        SQLiteDatabase db = admin.getWritableDatabase();

        admin.desasignarEvento(user_usuario,evento_nombre);

        db.close();
    }

    public void onResume(){
        super.onResume();
        //Registramos el listener
        sensorManager.registerListener(gyroListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void onStop(){
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    //Create a listener
    public SensorEventListener gyroListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float z = sensorEvent.values[2];

            if (z < -3f && cont == 0){
                cont++;
                last_update = sensorEvent.timestamp;
            }

            if(z > 5f && cont == 1){
                current_time = sensorEvent.timestamp;
                // Comprobamos que la diferencia de tiempo de los 2 movimientos es corta
                if (current_time - last_update < 2000000000) {
                    cont++;
                    button_eliminar_evento.performClick();
                    Toast.makeText(EditarEvento.this,"Se ha rechazado el evento", Toast.LENGTH_LONG).show();
                }else{
                    cont = 0;
                    last_update = 0;
                    current_time = 0;
                }
            }

            // Si hacemos toda la trnasición ponemos el contador a 0
            if (cont == 2){
                cont = 0;
            }
        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

}