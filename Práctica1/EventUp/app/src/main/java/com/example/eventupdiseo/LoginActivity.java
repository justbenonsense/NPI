package com.example.eventupdiseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView fondologin, logo;
    com.google.android.material.textfield.TextInputLayout usuario, contraseña;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        // Animaciones
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        // Identificaciones
        logo = findViewById(R.id.logologin);
        fondologin = findViewById(R.id.fondologin);
        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        login_button = findViewById(R.id.login_button);

        // Añadimos las animaciones a los objetos
        logo.setAnimation(topAnim);
        fondologin.setAnimation(topAnim);
        usuario.setAnimation(bottomAnim);
        contraseña.setAnimation(bottomAnim);
        login_button.setAnimation(topAnim);


    }

    private Boolean validateUsername() {
        String campo = usuario.getEditText().getText().toString();

        if (campo.isEmpty()) {
            usuario.setError("Este campo es obligatorio");
            return false;
        }
        else {
            usuario.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String campo = contraseña.getEditText().getText().toString();

        if (campo.isEmpty()) {
            contraseña.setError("Este campo es obligatorio");
            return false;
        }
        else {
            contraseña.setError(null);
            return true;
        }
    }

    public void loginUser(View view) {

        // Comprobamos si ha introducido usuario y contraseña
        if (!validateUsername() || !validatePassword()){
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {

        // Creamos un objeto de la clase DBHelper
        DBHelper admin = new DBHelper(this);

        // Abrimos la base de datos como escritura
        SQLiteDatabase db = admin.getWritableDatabase();

        // Creamos dos variables string con los datos ingresados por los usuarios
        String usuarioIntroducido = usuario.getEditText().getText().toString();
        String contraseñaIntroducida = contraseña.getEditText().getText().toString();

        // Inicializamos al cursor y llamamos al objeto de la base de datos
        Cursor fila = db.rawQuery("select USU_USUARIO,USU_CONTRASEÑA,USU_NOMBRE,USU_FOTO,USU_CARRERA from USUARIO where USU_USUARIO='"
                +usuarioIntroducido+"' and USU_CONTRASEÑA='"+contraseñaIntroducida+"'",null);

        // Hacemos un try catch para capturar los errores
        try {
            // Si el cursor tiene algún dato
            if(fila.moveToFirst()){
                String user=fila.getString(0);
                String password=fila.getString(1);

                // Comprobamos que el usuario y la contraseña son correctos
                if(usuarioIntroducido.equals(user) && contraseñaIntroducida.equals(password)){

                    String nombre = fila.getString(2);
                    String foto = fila.getString(3);
                    String grado = fila.getString(4);

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

                    intent.putExtra("usuario",usuarioIntroducido);
                    intent.putExtra("nombre",nombre);
                    intent.putExtra("foto",foto);
                    intent.putExtra("grado",grado);

                    startActivity(intent);
                    finish();
                }
            }
            else {
                Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
        }

        fila.close();
        db.close();

    }
}