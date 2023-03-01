package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity{
    // Obtenemos acceso a los sensores
    // Sensor de Proximidad
    SensorManager sensorManager;
    Sensor sensor;
    private Boolean sensorDisponible;
    private boolean cambio = true;
    SensorEventListener sensorProx;

    String user_usuario, user_nombre, user_grado, user_foto;

    // Sensor Acelerómetro
    SensorManager sensorMan;
    Sensor sensorAcelerometro;
    private Boolean sensorDisp;
    SensorEventListener sensorAcel;
    private int cont = 0;
    // Para controlar la diferencia de tiempo en el movimiento
    private long last_update = 0;
    private  long current_time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        showAllUserData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        // Sensor de Proximidad
        // Creamos un objeto SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Creamos un objeto sensor para el sensor de proximidad
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Comprobamos si existe el sensor en nuestro teléfono
        if (sensor != null) {
            sensorDisponible = true;
        } else {
            sensorDisponible = false;
            Toast.makeText(this, "El dispositivo no tiene sensor de proximidad", Toast.LENGTH_LONG).show();
            finish();
        }

        sensorProx = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] < sensor.getMaximumRange()){
                    cambioVista();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        // Acelerómetro
        // Creamos un objeto SensorManager
        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Creamos un objeto sensor para el sensor de proximidad
        sensorAcelerometro = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Comprobamos si existe el sensor en nuestro teléfono
        if (sensorMan != null) {
            sensorDisp = true;
        } else {
            sensorDisp = false;
            Toast.makeText(MenuActivity.this, "El dispositivo no tiene sensor de acelerómetro", Toast.LENGTH_LONG).show();
            finish();
        }

        sensorAcel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // Para salir de la app necesitamos realizar un movimiento algo brusco hacia atrás y hacia delante con el móvil

                float z = event.values[2];

                if (z < -10f && cont == 0){
                    cont++;
                    last_update = event.timestamp;
                }

                if(z > 10f && cont == 1){
                    current_time = event.timestamp;
                    // Comprobamos que la diferencia de tiempo de los 2 movimientos es corta
                    if (current_time - last_update < 300000000) {
                        cont++;
                        Intent j = new Intent(MenuActivity.this, LoginActivity.class);
                        startActivity(j);
                    }else{
                        cont = 0;
                        last_update = 0;
                        current_time = 0;
                    }
                }

                // Si hacemos toda la transición ponemos el contador a 0
                if (cont == 2){
                    cont = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorDisponible) {
            sensorManager.registerListener(sensorProx, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorDisp) {
            sensorManager.registerListener(sensorAcel, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorDisponible) {
            sensorManager.unregisterListener(sensorProx);
        }
        if (sensorDisp) {
            sensorManager.unregisterListener(sensorAcel);
        }
    }

    private void cambioVista() {
        if (cambio) {
            Intent i = new Intent (MenuActivity.this, CrearEvento.class);

            i.putExtra("usuario",user_usuario);
            i.putExtra("nombre",user_nombre);
            i.putExtra("foto",user_foto);
            i.putExtra("grado",user_grado);

            startActivity(i);
            cambio = false;
        }
        else{
            cambio = true;
        }
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        user_usuario = intent.getStringExtra("usuario");
        user_nombre = intent.getStringExtra("nombre");
        user_foto = intent.getStringExtra("foto");
        user_grado = intent.getStringExtra("grado");
    }
}