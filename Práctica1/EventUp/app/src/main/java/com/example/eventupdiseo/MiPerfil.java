package com.example.eventupdiseo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class MiPerfil extends Fragment {

    private TextView text_nombre;
    private ImageView image_foto;
    private Button button_grado, button_calificaciones, button_tui;
    androidx.appcompat.widget.Toolbar toolbar;
    String user_usuario, user_nombre, user_grado, user_foto;

    public MiPerfil() {
        // Required empty public constructor
    }

    public static MiPerfil newInstance() {
        MiPerfil fragment = new MiPerfil();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mi_perfil, container, false);
        text_nombre = (TextView) view.findViewById(R.id.texto_nombre);
        image_foto = (ImageView) view.findViewById(R.id.foto);
        button_grado = (Button) view.findViewById(R.id.button_grado);
        button_calificaciones = (Button) view.findViewById(R.id.button_calificaciones);
        button_tui = (Button) view.findViewById(R.id.button_tarjeta);
        toolbar = (androidx.appcompat.widget.Toolbar) view.findViewById(R.id.toolbar);

        toolbar.setTitle("");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        // Obtenemos la información del activity anterior
        showAllUserData();

        // Obtenemos la foto
        int resId = getResources().getIdentifier(user_foto,"drawable",getActivity().getPackageName());
        Drawable drawable = getActivity().getResources().getDrawable(resId);

        // Le asignamos la información a la vista
        text_nombre.setText(user_nombre);
        button_grado.setText(user_grado);
        image_foto.setImageDrawable(drawable);

        // Botón para acceder al activity Grado
        button_grado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir_grado = new Intent(getActivity(), Grado.class);

                ir_grado.putExtra("usuario",user_usuario);
                ir_grado.putExtra("nombre",user_nombre);
                ir_grado.putExtra("foto",user_foto);
                ir_grado.putExtra("grado",user_grado);

                startActivity(ir_grado);
            }
        });

        // Botón para acceder al activity Calificaciones
        button_calificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir_calificaciones = new Intent(getActivity(), Calificaciones.class);

                ir_calificaciones.putExtra("usuario",user_usuario);
                ir_calificaciones.putExtra("nombre",user_nombre);
                ir_calificaciones.putExtra("foto",user_foto);
                ir_calificaciones.putExtra("grado",user_grado);

                startActivity(ir_calificaciones);
            }
        });

        // Botón para acceder al activity Tui
        button_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir_tui = new Intent(getActivity(), Tui.class);

                ir_tui.putExtra("usuario",user_usuario);
                ir_tui.putExtra("nombre",user_nombre);
                ir_tui.putExtra("foto",user_foto);
                ir_tui.putExtra("grado",user_grado);

                startActivity(ir_tui);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bar_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAllUserData() {
        Intent intent = getActivity().getIntent();
        user_usuario = intent.getStringExtra("usuario");
        user_nombre = intent.getStringExtra("nombre");
        user_foto = intent.getStringExtra("foto");
        user_grado = intent.getStringExtra("grado");
    }
}