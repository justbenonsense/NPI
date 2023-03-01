package com.example.eventupdiseo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Database.AllEventsAdapter;
import Database.DBHelper;
import Database.Evento;
import Database.EventoAdapter;

public class EventUp extends Fragment {

    TextView texto_event_up;
    String user_usuario, user_nombre, user_grado, user_foto;
    String evento_nombre, evento_fecha, evento_hora, evento_ubicacion, evento_grado,evento_idioma;
    Integer evento_aforo=-1;
    ArrayList<Integer> tipoFiltro;
    ArrayList<String> datosFiltro;
    String sentencia_sql;

    RecyclerView lista_eventos;
    ArrayList<Evento> all_events;
    AllEventsAdapter eventoAdapter;

    ImageButton button_filtros_evento;

    public EventUp() {
        // Required empty public constructor
    }

    public static EventUp newInstance() {
        EventUp fragment = new EventUp();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_event_up, container, false);

        // Unimos con la parte visible
        button_filtros_evento = (ImageButton) view.findViewById(R.id.button_filtros_evento);
        texto_event_up = (TextView) view.findViewById(R.id.texto_event_up);
        lista_eventos =  (RecyclerView) view.findViewById(R.id.lista_todos_eventos);
        lista_eventos.setNestedScrollingEnabled(false);

        // Creamos un array
        all_events = new ArrayList<Evento>();
        tipoFiltro = new ArrayList<Integer>();
        datosFiltro = new ArrayList<String>();

        // Obtenemos la información del activity anterior
        showAllUserData();
        showAllEventFilterData();

        // Creamos un objeto de la clase DBHelper
        DBHelper admin = new DBHelper(getContext());

        // Abrimos la base de datos como escritura
        SQLiteDatabase db = admin.getWritableDatabase();

        // Vemos la sentencia sql
        sentencia_sql="select EVE_NOMBRE, EVE_FECHA, EVE_HORA, EVE_UBICACION, EVE_GRADO, EVE_IDIOMA, EVE_AFORO from EVENTO where EVE_NOMBRE not in (select UE_ID_EVENTO from USUARIO_EVENTO where UE_ID_USUARIO='"
                +user_usuario+"')";

        // Vemos si hay filtros y cuáles son
        if (hayFiltros()){
            sentencia_sql += " and ";

            for(int i = 0; i < tipoFiltro.size(); i++){
                int tipo = tipoFiltro.get(i);

                switch (tipo){
                    case 0:
                        sentencia_sql+="EVE_NOMBRE";
                        break;
                    case 1:
                        sentencia_sql+="EVE_FECHA";
                        break;
                    case 2:
                        sentencia_sql+="EVE_HORA";
                        break;
                    case 3:
                        sentencia_sql+="EVE_UBICACION";
                        break;
                    case 4:
                        sentencia_sql+="EVE_GRADO";
                        break;
                    case 5:
                        sentencia_sql+="EVE_IDIOMA";
                        break;
                    case 6:
                        sentencia_sql+="EVE_AFORO";
                        break;
                }

                sentencia_sql+="='"+datosFiltro.get(i)+"'";

                if (i!=tipoFiltro.size()-1){
                    sentencia_sql+=" and ";
                }
            }
        }

        Cursor fila = db.rawQuery(sentencia_sql,null);

        int num = fila.getCount();

        if (num > 0) {
            for (int i = 1; i <= num; i++) {

                if (fila.moveToNext()) {
                    String nombre_ev = fila.getString(0);
                    String fecha_ev = fila.getString(1);
                    String hora_ev = fila.getString(2);
                    String ubi_ev = fila.getString(3);
                    String grado_ev = fila.getString(4);
                    String idioma_ev = fila.getString(5);
                    Integer aforo_ev = fila.getInt(6);

                    Evento p = new Evento(nombre_ev, fecha_ev, hora_ev, aforo_ev, grado_ev, ubi_ev, idioma_ev);
                    all_events.add(p);
                }
            }

            eventoAdapter = new AllEventsAdapter(getContext(),all_events);
            lista_eventos.setAdapter(eventoAdapter);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
            lista_eventos.setLayoutManager(mLayoutManager);
            eventoAdapter.notifyDataSetChanged();
            DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(lista_eventos.getContext(), mLayoutManager.getOrientation());
            mDividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_rv));
            lista_eventos.addItemDecoration(mDividerItemDecoration);

        }

        fila.close();
        db.close();

        // Botón para filtrar los eventos
        button_filtros_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir_filtrar = new Intent(getActivity(), FilterActivity.class);

                ir_filtrar.putExtra("usuario",user_usuario);
                ir_filtrar.putExtra("nombre",user_nombre);
                ir_filtrar.putExtra("foto",user_foto);
                ir_filtrar.putExtra("grado",user_grado);

                startActivity(ir_filtrar);
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

    private void showAllEventFilterData() {
        Intent intent = getActivity().getIntent();
        evento_nombre = intent.getStringExtra("tituloeventofiltro");
        evento_fecha = intent.getStringExtra("fechaeventofiltro");
        evento_hora = intent.getStringExtra("horaeventofiltro");
        evento_ubicacion = intent.getStringExtra("ubicacioneventofiltro");
        evento_grado = intent.getStringExtra("gradoeventofiltro");
        evento_idioma = intent.getStringExtra("idiomaeventofiltro");
        evento_aforo = intent.getIntExtra("aforoeventofiltro",-1);
    }

    private Boolean hayFiltros() {

        Boolean hayFiltros = false;

        if ( evento_nombre != null){
            if (evento_nombre.length() != 0) {
                hayFiltros = true;
                datosFiltro.add(evento_nombre);
                tipoFiltro.add(0);
            }
        }

        if ( evento_fecha != null){
            if (evento_fecha.length() != 0 ) {
                hayFiltros = true;
                datosFiltro.add(evento_fecha);
                tipoFiltro.add(1);
            }
        }

        if ( evento_hora != null) {
            if (evento_hora.length() != 0) {
                hayFiltros = true;
                datosFiltro.add(evento_hora);
                tipoFiltro.add(2);
            }
        }
        if ( evento_ubicacion != null){
            if (evento_ubicacion.length() != 0) {
                hayFiltros = true;
                datosFiltro.add(evento_ubicacion);
                tipoFiltro.add(3);
            }
        }
        if ( evento_grado != null){
            if(evento_grado.length() != 0) {
                hayFiltros = true;
                datosFiltro.add(evento_grado);
                tipoFiltro.add(4);
            }
        }
        if ( evento_idioma != null){
            if(evento_idioma.length() != 0) {
                hayFiltros = true;
                datosFiltro.add(evento_idioma);
                tipoFiltro.add(5);
            }
        }
        if ( evento_aforo != -1){
            hayFiltros = true;
            datosFiltro.add(evento_aforo.toString());
            tipoFiltro.add(6);
        }

        return hayFiltros;
    }
}