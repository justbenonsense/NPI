package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Grado extends AppCompatActivity {

    private ImageButton button_back_grado;
    private ImageButton button_ubi_facultad;
    private ImageButton button_ubi_facultad2;

    String user_usuario, user_nombre, user_grado, user_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grado);

        button_back_grado = findViewById(R.id.button_back_grado);
        button_ubi_facultad = findViewById(R.id.button_ubi_facultad);
        button_ubi_facultad2 = findViewById(R.id.button_ubi_facultad2);


        // Vemos la información del usuario
        showAllUserData();

        button_back_grado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button_ubi_facultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( ContextCompat.checkSelfPermission( Grado.this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( Grado.this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 0);
                    return;
                }
                if ( ContextCompat.checkSelfPermission( Grado.this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( Grado.this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  }, 1);
                    return;
                }

                double destinationLatitude = 37.179292;
                double destinationLongitude = -3.608297;
                String uri = "http://maps.google.com/maps?daddr=" + destinationLatitude + "," + destinationLongitude;
                Uri gmmIntentUri = Uri.parse(uri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

            }
        });

        button_ubi_facultad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( ContextCompat.checkSelfPermission( Grado.this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( Grado.this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 0);
                    return;
                }
                if ( ContextCompat.checkSelfPermission( Grado.this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( Grado.this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  }, 1);
                    return;
                }

                double destinationLatitude = 37.197125;
                double destinationLongitude = -3.625097;
                String uri = "http://maps.google.com/maps?daddr=" + destinationLatitude + "," + destinationLongitude;
                Uri gmmIntentUri = Uri.parse(uri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
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