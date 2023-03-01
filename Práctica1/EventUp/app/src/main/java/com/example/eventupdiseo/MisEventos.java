package com.example.eventupdiseo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DBHelper;
import Database.Evento;
import Database.EventoAdapter;


public class MisEventos extends Fragment {

    TextView texto_mis_eventos, endpage;
    String user_usuario, user_nombre, user_grado, user_foto;
    ImageButton button_crear_evento;

    RecyclerView lista_eventos;
    ArrayList<Evento> mis_eventos;
    EventoAdapter eventoAdapter;

    public MisEventos() {
        // Required empty public constructor
    }


    public static MisEventos newInstance() {
        MisEventos fragment = new MisEventos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mis_eventos, container, false);

        // Unimos con la parte visible
        texto_mis_eventos = (TextView) view.findViewById(R.id.texto_mis_eventos);
        endpage = (TextView) view.findViewById(R.id.endpage);
        lista_eventos =  (RecyclerView) view.findViewById(R.id.lista_eventos);
        button_crear_evento = (ImageButton) view.findViewById(R.id.button_crear_evento);

        lista_eventos.setNestedScrollingEnabled(false);

        // Creamos un array
        mis_eventos = new ArrayList<Evento>();

        // Obtenemos la información del activity anterior
        showAllUserData();

        // Creamos un objeto de la clase DBHelper
        DBHelper admin = new DBHelper(getContext());

        // Abrimos la base de datos como escritura
        SQLiteDatabase db = admin.getWritableDatabase();

        // Inicializamos al cursor y llamamos al objeto de la base de datos
        Cursor fila = db.rawQuery("select UE_ID_EVENTO, EVE_FECHA, EVE_HORA, EVE_AFORO, EVE_GRADO, EVE_UBICACION, EVE_IDIOMA from EVENTO e join (select * from USUARIO_EVENTO where UE_ID_USUARIO='"
                +user_usuario+"') v ON (e.EVE_NOMBRE = v.UE_ID_EVENTO)",null);

        int num = fila.getCount();

        if (num > 0) {
            for (int i = 1; i <= num; i++) {

                if (fila.moveToNext()) {
                    String nombre_ev = fila.getString(0);
                    String fecha_ev = fila.getString(1);
                    String hora_ev = fila.getString(2);
                    Integer aforo_ev = fila.getInt(3);
                    String grado_ev = fila.getString(4);
                    String ubi_ev = fila.getString(5);
                    String idioma_ev = fila.getString(6);

                    Evento p = new Evento(nombre_ev, fecha_ev, hora_ev, aforo_ev, grado_ev, ubi_ev, idioma_ev);
                    mis_eventos.add(p);
                }
            }

            eventoAdapter = new EventoAdapter(getContext(),mis_eventos, getActivity());
            lista_eventos.setAdapter(eventoAdapter);
            lista_eventos.setLayoutManager(new LinearLayoutManager(getContext()));
            eventoAdapter.notifyDataSetChanged();
        }


        fila.close();
        db.close();

        // Botón para crear un evento
        button_crear_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir_crear = new Intent(getActivity(), CrearEvento.class);

                ir_crear.putExtra("usuario",user_usuario);
                ir_crear.putExtra("nombre",user_nombre);
                ir_crear.putExtra("foto",user_foto);
                ir_crear.putExtra("grado",user_grado);

                startActivity(ir_crear);
            }
        });

        return view;
    }

    private void showAllUserData() {
        Intent intent = getActivity().getIntent();
        user_usuario = intent.getStringExtra("usuario");
        user_nombre = intent.getStringExtra("nombre");
        user_foto = intent.getStringExtra("foto");
        user_grado = intent.getStringExtra("grado");
    }
}